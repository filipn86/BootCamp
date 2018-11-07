package pl.coreservices.model.web;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StatisticKryoSerializer implements StreamSerializer<Statistic> {

    private static final ThreadLocal<Kryo> kryoThreadLocal
            = new ThreadLocal() {

        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.register(Statistic.class);
            return kryo;
        }
    };

    public StatisticKryoSerializer() {
    }

    public int getTypeId() {
        return 2;
    }

    public void write(ObjectDataOutput objectDataOutput,
                      Statistic statistic)
            throws IOException {
        Kryo kryo = kryoThreadLocal.get();
        Output output = new Output((OutputStream) objectDataOutput);
        kryo.writeObject(output, statistic);
        output.flush();
    }

    public Statistic read(ObjectDataInput objectDataInput)
            throws IOException {
        InputStream in = (InputStream) objectDataInput;
        Input input = new Input(in);
        Kryo kryo = kryoThreadLocal.get();
        return kryo.readObject(input, Statistic.class);
    }

    public void destroy() {
    }
}