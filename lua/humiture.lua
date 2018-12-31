alt=320 -- altitude of the measurement place

sda, scl = 3, 4
i2c.setup(0, sda, scl, i2c.SLOW) -- call i2c.setup() only once
bme280.setup()


function  readHumiture() 
  T, P, H, QNH = bme280.read(alt)
  local Tsgn = (T < 0 and -1 or 1); T = Tsgn*T
    
  local strT = string.format("%s%d.%02d", Tsgn<0 and "-" or "", T/100, T%100);
    
  print("T="..strT)
  local strQFE = string.format("%d.%03d", P/1000, P%1000);
  print("QFE="..strQFE)
  local strQNH = string.format("%d.%03d", QNH/1000, QNH%1000);
  print("QNH="..strQNH)
  local strHumidity = string.format("%d.%03d", H/1000, H%1000);
  print("humidity="..strHumidity)
  
  D = bme280.dewpoint(H, T)
  local Dsgn = (D < 0 and -1 or 1); D = Dsgn*D
  local strDew_point = string.format("%s%d.%02d", Dsgn<0 and "-" or "", D/100, D%100);
  print("dew_point="..strDew_point)
   
    
    -- altimeter function - calculate altitude based on current sea level pressure (QNH) and measure pressure
  P = bme280.baro()
  curAlt = bme280.altitude(P, QNH)
  local curAltsgn = (curAlt < 0 and -1 or 1); curAlt = curAltsgn*curAlt
  local strAltitude = string.format("%s%d.%02d", curAltsgn<0 and "-" or "", curAlt/100, curAlt%100);
  print("altitude="..strAltitude)
  local returnStr = string.format("{\"T\":%s,\"QFE\":%s,\"QNH\":%s,\"humidity\":%s,\"dew_point\":%s,\"altitude\":%s,\"componentId\":1}"
     , strT, strQFE , strQNH ,strHumidity,strDew_point ,strAltitude  );
  print(returnStr)
  return returnStr
end
