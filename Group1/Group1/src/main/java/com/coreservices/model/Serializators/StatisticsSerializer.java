package com.coreservices.model.Serializators;

import com.coreservices.model.Statistic;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.InputStream;
import java.io.OutputStream;

public class StatisticsSerializer implements StreamSerializer<Statistic> {

    private static final ThreadLocal<Kryo> kryoThreadLocal
            = new ThreadLocal() {

        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.register(Statistic.class);
            return kryo;
        }
    };

    @Override
    public void write(ObjectDataOutput objectDataOutput, Statistic statistic) {
        Kryo kryo = kryoThreadLocal.get();
        Output output = new Output((OutputStream) objectDataOutput);
        kryo.writeObject(output, statistic);
        output.flush();
    }

    @Override
    public Statistic read(ObjectDataInput objectDataInput) {
        InputStream in = (InputStream) objectDataInput;
        Input input = new Input(in);
        Kryo kryo = kryoThreadLocal.get();
        return kryo.readObject(input, Statistic.class);
    }

    @Override
    public int getTypeId() {
        return 0;
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException();
    }
}
