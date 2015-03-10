package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Observable;
import java.util.Set;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import virustotalapi.ReportFileScan;
import virustotalapi.ReportScan;
import virustotalapi.VirusTotal;

/**
 * Application Model
 * @author Estado
 */

public class Model extends Observable
{
	private VirusTotal vT;
	private String apiKey = "5709e9522ba045c98bc3177bfad1ef3fef22d6b24c5d4dcce3e4fa6acc126291"; // Your api key
	private String fileHash; // SHA256 file hash
	private String fileUrl; // URL file
	private String reportScan;
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Constructor sin parametros.
	 */
	public Model()
	{
		this.vT = new VirusTotal(apiKey);
		this.reportScan = "";
	}
	
	/**
	 * Devuelve el scan report del fichero especificado
	 * @param fileHash - String crypted Sha256
	 * @throws IOException
	 */
	public void reportScan(String fileHash) throws IOException
	{
		this.reportScan = "";
		try
		{
			Set <ReportScan> Report = this.vT.ReportScan(fileHash);

			for(ReportScan report : Report)
			{
				if(report == null)
					throw new IOException();
				//System.out.println("AV: "+report.getVendor()+" Detected: "+report.getDetected()+" Update: "+report.getUpdate()+" Malware Name: "+report.getMalwarename());
				this.reportScan += "[+] AV: "+report.getVendor()+" || Detected: "+report.getDetected()+" || Malware Name: "+report.getMalwarename() + LINE_SEPARATOR; 
				
			}
		}catch(NullPointerException e)
		{
			throw new IOException();
		}
	}
	
	/**
	 * Sube el archivo a VirusTotal y devuelve la url y el scanid(sha256)/filehash.
	 * @param filePath
	 * @throws IOException
	 */
	public void UploadAndReport(String filePath) throws IOException
	{
        Set <ReportFileScan> Report = this.vT.sendFileScan(filePath); 
        for(ReportFileScan report : Report)
        {
        	//System.out.println("URL: "+report.getPermaLink()+" Response code: "+report.getResponseCode());
        	this.fileUrl = report.getPermaLink();
        }
        this.fileHash = FindFileHash();
	}

	/**
	 * Busca el filehash dentro de la url.
	 * @return filehash
	 */
	private String FindFileHash() 
	{
		String url = this.fileUrl;
		String[] words = url.split("/");
		return words[4];
	}
	
	//////////////////////////////////// GETTERS
	
	public String getFileHash()
	{
		return this.fileHash;
	}
	
	public String getFileUrl()
	{
		return this.fileUrl;
	}
	
	public String getReportScan()
	{
		return this.reportScan;
	}


}
