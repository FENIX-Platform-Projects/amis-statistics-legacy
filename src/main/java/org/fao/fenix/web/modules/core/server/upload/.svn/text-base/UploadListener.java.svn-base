/* Licence:
 *   Use this however/wherever you like, just don't blame me if it breaks anything.
 *
 * Credit:
 *   If you're nice, you'll leave this bit:
 *
 *   Class by Pierre-Alexandre Losson -- http://www.telio.be/blog
 *   email : plosson@users.sourceforge.net
 */
package org.fao.fenix.web.modules.core.server.upload;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * 
 * @author Original : plosson on 06-janv.-2006 15:05:44 - Last modified by $Author: vde $ on $Date: 2004/11/26 22:43:57 $
 * @version 1.0 - Rev. $Revision: 1.2 $
 */
public class UploadListener implements OutputStreamListener {
	private final static Logger LOG = Logger.getLogger(UploadListener.class);
    private HttpServletRequest request;
    private long delay = 0;
    private long startTime = 0;
    private long totalToRead = 0;
    private long totalBytesRead = 0;
    private int totalFiles = -1;

    public UploadListener(HttpServletRequest request, long debugDelay) {
        this.request = request;
        this.delay = debugDelay;
        totalToRead = request.getContentLength();
        this.startTime = System.currentTimeMillis();
    }

    public void start() {
        totalFiles++;
        updateUploadInfo("start");

		lastmillis = System.currentTimeMillis();
    }

	private long lastloggedbyte = 0;
	private long lastmillis     = 0;

    public void bytesRead(int bytesRead) {
        totalBytesRead = totalBytesRead + bytesRead;
        updateUploadInfo("progress");

		if(System.currentTimeMillis() - lastmillis > 15000) {
			updateStatsAndLog();
		}

		// This delay will severely limit the upload bandwidth: what is it needed for?
//        try {
//            Thread.sleep(delay);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

	private void updateStatsAndLog() {
		long now = System.currentTimeMillis();
		float kibpersec = ((float)totalBytesRead ) / (float)(now-startTime);
		int percent = (int)((100*totalBytesRead)/totalToRead);
		LOG.info("Uploaded " + totalBytesRead + "/" + totalToRead +" bytes ("+ percent+"%) "+kibpersec + "KB/s");
		lastloggedbyte = totalBytesRead;
		lastmillis = now;
	}

    public void error(String message) {
        updateUploadInfo("error");
    }

    public void done() {
        updateUploadInfo("done");
		updateStatsAndLog();
    }

    private void updateUploadInfo(String status) {
        long delta = (System.currentTimeMillis() - startTime) / 1000;
        request.getSession().setAttribute("uploadInfo",
                new UploadInfo(totalFiles, totalToRead, totalBytesRead, delta, status));
    }

}
