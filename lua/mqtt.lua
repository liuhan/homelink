-- init mqtt client without logins, keepalive timer 120s
m = mqtt.Client("humiture", 120)



m:on("connect", function(client) print ("connected") end)
m:on("offline", function(client) print ("offline") end)

-- on publish message receive event
m:on("message", function(client, topic, data) 
  print(topic .. ":" ) 
  if data ~= nil then
    print("receive:"..data)
  end
end)

-- on publish overflow receive event
m:on("overflow", function(client, topic, data)
  print(topic .. " partial overflowed message: " .. data )
end)


function  sendData()  
  m:publish("/homecenter",readHumiture(), 0, 0, function(client) print("sent") end)
end

