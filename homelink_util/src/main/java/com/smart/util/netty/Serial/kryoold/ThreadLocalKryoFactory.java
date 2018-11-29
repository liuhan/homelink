package com.smart.util.netty.Serial.kryoold;

import com.esotericsoftware.kryo.Kryo;
import com.kelent.util.netty.Serial.kryoold.KryoFactory;

/**
 * Author : syl
 * datetime ：2018/4/9.
 */


public class ThreadLocalKryoFactory extends KryoFactory {

    private final ThreadLocal<Kryo> holder  = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            return createKryo();
        }
    };

    public   Kryo  getKryo() {
        return holder.get();
    }
}