-- load credentials, 'SSID' and 'PASSWORD' declared and initialize in there
dofile("network.lua")
dofile("humiture.lua")
dofile("mqtt.lua")
-- Register WiFi Station event callbacks
wifi.eventmon.register(wifi.eventmon.STA_CONNECTED, wifi_connect_event)
wifi.eventmon.register(wifi.eventmon.STA_GOT_IP, wifi_got_ip_event)
wifi.eventmon.register(wifi.eventmon.STA_DISCONNECTED, wifi_disconnect_event)

print("Connecting to WiFi access point...")
wifi.setmode(wifi.STATION)
wifi.sta.config({ssid="kelent", pwd="kelent00"})

readHumiture()
