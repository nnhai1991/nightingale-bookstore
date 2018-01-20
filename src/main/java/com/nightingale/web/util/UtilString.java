package com.nightingale.web.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;

public class UtilString
{

	private static final String	PASSWORD_PATTERN	= "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]))";
	private static Pattern		pattern;
	private static Matcher		matcher;

	
	public UtilString()
	{
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}	
	
	public static boolean minLengthCheck(String str, int min)
	{
		return str.length() >= min;
	}

	public static boolean maxLengthCheck(String str, int max)
	{
		return str.length() < max;
	}

	public static String escapeSpecialCharacter(String str)
	{
		return str.replaceAll("(<>!?=[]\\[+&|!(){}^\'\"~*?:\\\\-])", "");
	}

	public static boolean validatePasswordComplexity(String password)
	{
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static String GetVendorDisplayID(Integer id)
	{
		return lPadZero(id, 10);
	}

	public static String GetViewDisplayID(Integer id, String prefix)
	{
		return prefix + lPadZero(id, 10);
	}


	/**
	 * @author kyaw.mt
	 *
	 */
	public static Integer GetOrginalID(String OrgStr)
	{
		String rtnStr = "";
		int length = OrgStr.length();

		for (int i = 0; i < length; i++)
		{

			boolean flag = false;
			try
			{
				int x = Integer.parseInt(String.valueOf(OrgStr.charAt(i)));
				if (x == 0)
				{
					if (rtnStr.length() > 0)
						flag = true;
				} else
				{

					return Integer.parseInt(OrgStr.substring(i, OrgStr.length()));
				}
			} catch (Exception e)
			{
				flag = true;
			}
			if (flag)
			{
				if (rtnStr.length() > 0)
				{
					rtnStr = rtnStr.substring(1);

				} else
				{
					rtnStr = OrgStr.substring(1);
				}
			}
		}
		Integer a = 0;
		if (rtnStr.length() > 0)
		{
			a = Integer.parseInt(rtnStr);
			return a;
		} else
		{
			try
			{

				return Integer.parseInt(OrgStr);
			} catch (Exception e)
			{
				return -1;
			}
		}

	}

	/**
	 * @param in
	 *            The integer value
	 * @param fill
	 *            The number of digits to fill
	 * @return The given value left padded with the given number of digits
	 */
	public static String lPadZero(int in, int fill)
	{

		boolean negative = false;
		int value, len = 0;

		if (in >= 0)
		{
			value = in;
		} else
		{
			negative = true;
			value = -in;
			in = -in;
			len++;
		}

		if (value == 0)
		{
			len = 1;
		} else
		{
			for (; value != 0; len++)
			{
				value /= 10;
			}
		}

		StringBuilder sb = new StringBuilder();

		if (negative)
		{
			sb.append('-');
		}

		for (int i = fill; i > len; i--)
		{
			sb.append('0');
		}

		sb.append(in);

		return sb.toString();
	}
	
	public static void setLocale(String locale, Model model)
	{
		String lang = locale.toLowerCase();
		
		if (lang.equalsIgnoreCase("in_id"))
		{
			model.addAttribute("locale", "id");
		}
		else
		{
			// Default
			model.addAttribute("locale", "en");
		}
	}
	
	public static String GenerateRandomString(int minLength, int maxLength, int minLCaseCount, int minUCaseCount, int minNumCount, int minSpecialCount)
	{
	    char[] randomString;

	    String lCaseChars = "abcdefgijkmnopqrstwxyz";
	    String uCaseChars = "ABCDEFGHJKLMNPQRSTWXYZ";
	    String numericChars = "0123456789";
	    String specialChars = "!@#$%^&+=";

	    Map<String,Integer> charGroupsUsed = new HashMap<String,Integer>();
	    charGroupsUsed.put("lcase", minLCaseCount);
	    charGroupsUsed.put("ucase", minUCaseCount);
	    charGroupsUsed.put("num", minNumCount);
	    charGroupsUsed.put("special", minSpecialCount);

	    // Because we cannot use the default randomizer, which is based on the
	    // current time (it will produce the same "random" number within a
	    // second), we will use a random number generator to seed the
	    // randomizer.

	    // Use a 4-byte array to fill it with random bytes and convert it then
	    // to an integer value.
	    byte[] randomBytes = new byte[4];

	    // Generate 4 random bytes.
	    new Random().nextBytes(randomBytes);

	    // Convert 4 bytes into a 32-bit integer value.
	    int seed = (randomBytes[0] & 0x7f) << 24 |
	                randomBytes[1] << 16 |
	                randomBytes[2] << 8 |
	                randomBytes[3];

	    // Create a randomizer from the seed.
	    Random random = new Random(seed);

	    // Allocate appropriate memory for the password.
	    int randomIndex = -1;
	    if (minLength < maxLength)
	    {
	        randomIndex = random.nextInt((maxLength-minLength)+1)+minLength;
	        randomString = new char[randomIndex];
	    }
	    else
	    {
	        randomString = new char[minLength];
	    }

	    int requiredCharactersLeft = minLCaseCount + minUCaseCount + minNumCount + minSpecialCount;

	    // Build the password.
	    for (int i = 0; i < randomString.length; i++)
	    {
	        String selectableChars = "";

	        // if we still have plenty of characters left to acheive our minimum requirements.
	        if (requiredCharactersLeft < randomString.length - i)
	        {
	            // choose from any group at random
	            selectableChars = lCaseChars + uCaseChars + numericChars + specialChars;
	        }
	        else // we are out of wiggle room, choose from a random group that still needs to have a minimum required.
	        {
	            // choose only from a group that we need to satisfy a minimum for.
	            for(Map.Entry<String, Integer> charGroup : charGroupsUsed.entrySet())
	            {
	                if (charGroup.getValue() > 0)
	                {
	                    if("lcase".equals(charGroup.getKey()) ){
	                        selectableChars += lCaseChars;
	                    }
	                    else if("ucase".equals(charGroup.getKey())){
	                        selectableChars += uCaseChars;
	                    }
	                    else if("num".equals(charGroup.getKey())){
	                        selectableChars += numericChars;
	                    }
	                    else if("special".equals(charGroup.getKey())){
	                        selectableChars += specialChars;
	                    }
	                }
	            }
	        }

	        // Now that the string is built, get the next random character.
	        randomIndex = random.nextInt((selectableChars.length())-1);
	        char nextChar = selectableChars.charAt(randomIndex);

	        // Tac it onto our password.
	        randomString[i] = nextChar;

	        // Now figure out where it came from, and decrement the appropriate minimum value.
	        if (lCaseChars.indexOf(nextChar) > -1)
	        {
	            charGroupsUsed.put("lcase",charGroupsUsed.get("lcase") - 1);
	            if (charGroupsUsed.get("lcase") >= 0)
	            {
	                requiredCharactersLeft--;
	            }
	        }
	        else if (uCaseChars.indexOf(nextChar) > -1)
	        {
	            charGroupsUsed.put("ucase",charGroupsUsed.get("ucase") - 1);
	            if (charGroupsUsed.get("ucase") >= 0)
	            {
	                requiredCharactersLeft--;
	            }
	        }
	        else if (numericChars.indexOf(nextChar) > -1)
	        {
	            charGroupsUsed.put("num", charGroupsUsed.get("num") - 1);
	            if (charGroupsUsed.get("num") >= 0)
	            {
	                requiredCharactersLeft--;
	            }
	        }
	        else if (specialChars.indexOf(nextChar) > -1)
	        {
	            charGroupsUsed.put("special",charGroupsUsed.get("special") - 1);
	            if (charGroupsUsed.get("special") >= 0)
	            {
	                requiredCharactersLeft--;
	            }
	        }
	    }
	    
	    return new String(randomString);
	}
}
