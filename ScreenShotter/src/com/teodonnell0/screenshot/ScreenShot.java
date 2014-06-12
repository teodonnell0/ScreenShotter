package com.teodonnell0.screenshot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

public class ScreenShot {

	private int width,
	height,
	xVal,
	yVal,
	delay;
	private String format;
	private boolean copy;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


	public ScreenShot(int w, int h, int x, int y, int d, String fmt, boolean c)
	{
		if(w < 0)
			this.width = dim.width;
		else
			this.width = w;

		if(h < 0)
			this.height = dim.height;
		else
			this.height = h;

		if(x < 0 || x+this.width > dim.width)
			this.xVal = 0;
		else
			this.xVal = x;

		if(y < 0 || y+this.height > dim.height)
			this.yVal = 0;
		else
			this.yVal = y;

		if(d < 0)
			this.delay = 0;
		else
			this.delay = d;

		this.copy = c;
		this.format = fmt;
		
		takeScreenShot();
	}

	public ScreenShot(int d, String fmt)
	{
		this.width = dim.width;
		this.height = dim.height;
		this.xVal = 0;
		this.yVal = 0;
		this.delay = d;
		this.format = fmt;
	}

	
	private void takeScreenShot()
	{
		BufferedImage capture = captureScreenShot();
		if(copy)
		{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			
		}
		try
		{
			ImageIO.write(capture, format, new File(getFileName()));
			System.out.println("File wrote "+getFileName());
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private BufferedImage captureScreenShot()
	{
		BufferedImage cpt = null;
		try
		{
			Rectangle rct = new Rectangle(xVal, yVal, width, height);
			Robot 	  rbt = new Robot();
			rbt.delay(delay);
			cpt = rbt.createScreenCapture(rct);
		} catch (AWTException e)
		{
			e.printStackTrace();
		}
		return cpt;
	}
	
	private String getFileName()
	{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		String name = fmt.format(Calendar.getInstance().getTime());
		return name + "." + format;
	}


}
