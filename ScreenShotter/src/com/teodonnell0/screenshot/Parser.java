package com.teodonnell0.screenshot;

import java.util.logging.Logger;

public class Parser {

	private final Logger log = Logger.getLogger("Client");


	public Parser(String[] args)
	{
		boolean verboseMode = false;
		int 	w = -1,
				h = -1,
				x = 0,
				y = 0,
				d = -1,
				fullScreen = 0;
		boolean copy = false;
		int i = 0;
		String format = "JPG";
		String arg;

		while(i < args.length && args[i].startsWith("-"))
		{
			arg = args[i++];

			switch(arg.charAt(1))
			{
				case 'c':
				{
					copy = true;
					break;
				}
				case 'v': // verbose mode
				{
					verboseMode = true;
					log.info("Verbose mode turned on");
					break;
				}
				
				case 'w': // set width
				{
					arg = args[i++];
					if(checkValidInteger(arg))
					{
						w = Integer.parseInt(arg);
						break;
					}
					else
						error('w', arg, "Expected integer");
				}
				
				case 'h': //set height
				{
					arg = args[i++];
					if(checkValidInteger(arg))
					{
						h = Integer.parseInt(arg);
						break;
					}
					else
						error('h', arg, "Expected integer");
				}
				
				case 'x': //set x value
				{
					arg = args[i++];
					if(checkValidInteger(arg))
					{
						w = Integer.parseInt(arg);
						break;
					}
					else
						error('x', arg, "Expected integer");
				}
				
				case 'y': //set y value
				{
					arg = args[i++];
					if(checkValidInteger(arg))
					{
						w = Integer.parseInt(arg);
						break;
					}
					else
						error('y', arg, "Expected integer");
				}
				
				case 'f':	//set format
				{
					arg = args[i++];
					if(arg.equalsIgnoreCase("jpg") || arg.equalsIgnoreCase("jpeg"))
					{
						format = "JPG";
						break;
					}
					if(arg.equalsIgnoreCase("png"))
					{
						format = "PNG";
						break;
					}
					if(arg.equalsIgnoreCase("bmp"))
					{
						format = "BMP";
						break;
					}
					
					error('f', arg, "Accepted parameters: JPG, PNG, BMP");
				}
				
				case 'F': //set full screen
				{
					fullScreen = 1;
					break;
				}
				
				case 'd': //set delay
				{
					arg = args[i++];
					if(checkValidInteger(arg))
					{
						d = Integer.parseInt(arg);
						break;
					}
					else
						error('h', arg, "Expected integer");	
				}
			}
		}
		
		
	}
	
	private void error(char parameter, String value, String message)
	{
		throw new Error("Invalid value for parameter " + parameter + ": value\n" + message);
	}
	
	private boolean checkValidInteger(String s)
	{
		if(s.matches("\\d+"))
			return true;
		return false;
	}
}
