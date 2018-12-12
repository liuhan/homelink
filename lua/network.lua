function startup()
    if file.open("init.lua") == nil then
        print("init.lua deleted or renamed")
    else
        print("Running")
        file.close("init.lua")
        -- the actual application is stored in 'application.lua'
        -- dofile("application.lua")
    end
end 
-- Define WiFi station event callbacks 
wifi_connect_event = function(T) 
  print("Connection to AP("..T.SSID..") established!")
  print("Waiting for IP address...")
  if disconnect_ct ~= nil then disconnect_ct = nil end  
end

wifi_got_ip_event = function(T) 
  -- Note: Having an IP address does not mean there is internet access!
  -- Internet connectivity can be determined with net.dns.resolve().    
  print("Wifi connection is ready! IP address is: "..T.IP)
  print("Startup will resume momentarily, you have 3 seconds to abort.")
  print("Waiting...") 
  tmr.create():alarm(3000, tmr.ALARM_SINGLE, startup)

  -- for TLS: m:connect("192.168.11.118", secure-port, 1)
  m:connect("112.126.90.93", 8888, 0, function(client)
    print("connected")
      -- Calling subscribe/publish only makes sense once the connection
      -- was successfully established. You can do that either here in the
      -- 'connect' callback or you need to otherwise make sure the
      -- connection was established (e.g. tracking connection status or in
      -- m:on("connect", function)).
    
      -- subscribe topic with qos = 0
    client:subscribe("/homelink", 0, function(client) print("subscribe success") end)

    tmr.alarm(0, 2000, tmr.ALARM_AUTO, sendData)
    
      -- publish a message with data = hello, QoS = 0, retain = 0
      -- client:publish("/homelink", "hello", 0, 0, function(client) print("sent") end)
  end,
  function(client, reason)
    print("failed reason: " .. reason)
  end)
end

wifi_disconnect_event = function(T)
  if T.reason == wifi.eventmon.reason.ASSOC_LEAVE then 
    --the station has disassociated from a previously connected AP
    return 
  end
  -- total_tries: how many times the station will attempt to connect to the AP. Should consider AP reboot duration.
  local total_tries = 75
  print("\nWiFi connection to AP("..T.SSID..") has failed!")

  --There are many possible disconnect reasons, the following iterates through 
  --the list and returns the string corresponding to the disconnect reason.
  for key,val in pairs(wifi.eventmon.reason) do
    if val == T.reason then
      print("Disconnect reason: "..val.."("..key..")")
      break
    end
  end

  if disconnect_ct == nil then 
    disconnect_ct = 1 
  else
    disconnect_ct = disconnect_ct + 1 
  end
  if disconnect_ct < total_tries then 
    print("Retrying connection...(attempt "..(disconnect_ct+1).." of "..total_tries..")")
  else
    wifi.sta.disconnect()
    print("Aborting connection to AP!")
    disconnect_ct = nil  
  end
end

 