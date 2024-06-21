package org.acme;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class GreetingMessageBodyWriter implements MessageBodyWriter<String> {


    @Override
    public boolean isWriteable(final Class<?> aClass, final Type type, final Annotation[] annotations,
                               final MediaType mediaType) {
        return String.class.isAssignableFrom(aClass) && MediaType.TEXT_PLAIN_TYPE.isCompatible(mediaType);
    }

    @Override
    public void writeTo(final String s, final Class<?> aClass, final Type type, final Annotation[] annotations,
                        final MediaType mediaType,
                        final MultivaluedMap<String, Object> multivaluedMap, final OutputStream outputStream)
            throws IOException, WebApplicationException {

        final var content = "Greeting response: " + s;
        outputStream.write(content.getBytes());
    }
}
