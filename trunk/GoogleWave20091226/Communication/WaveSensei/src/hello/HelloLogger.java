package hello;

import java.util.logging.Logger;

import org.kyotogtug.wavesensei.util.XMPPHandler;

public class HelloLogger {

  private static Logger logger =
    Logger.getLogger(HelloLogger.class.getSimpleName());

  private static final String LOG_NOTIFY_TO = "WaveSensei@gmail.com";

  static {
    logger.addHandler(new XMPPHandler(LOG_NOTIFY_TO));
  }
  
  public void sampleWarning(){
    logger.warning("サンプルです");
  }

}
