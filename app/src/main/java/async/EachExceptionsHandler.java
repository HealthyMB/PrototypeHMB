package async;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Created by Richard on 08/03/2016.
 */
public interface EachExceptionsHandler {
    void handleIOException(IOException e);
    void handleMalformedURLException(MalformedURLException e);
    void handleProtocolException(ProtocolException e);
    void handleUnsupportedEncodingException(UnsupportedEncodingException e);
}
