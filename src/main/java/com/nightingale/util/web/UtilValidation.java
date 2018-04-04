package com.nightingale.util.web;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hai
 */
public class UtilValidation {

    public static final String LOGIN_VALID_EMAIL = "(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)";

    public static final String EMAIL_WITH_DOMAIN = "[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";

    public static final String EMAIL_ALLOW = "^[^@]+@[a-zA-Z0-9._-]+$";
    public static final String EMAIL_CHARACTERS = "^[a-zA-Z0-9._@-]+$";

    public static final String PASSWORD_ALLOW = "(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}";
    public static final String PASSWORD_SEQ = ".*(012|123|234|345|456|567|678|789).*";
    public static final String MIN8LENGTH_MIN1LOWERCASE_MIN1UPPERCASE_MIN1DIGIT_MIN1SPECIALCHAR = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    // No padding and trailing space
    public static final String ADDRESS = "^[,=/A-Za-z.0-9 #_&()?-]*";
    // public static final String PHONE_NUMBER_8_DIGIT = "^[(+0-9)]*[0-9-() ]*$";
//    /public static final String PHONE_NUMBER_8_DIGIT = "^[+]?[0-9 ]*[(]?[+]?[0-9 ]+[)]?([-]?[0-9 ])*$"; // "^[0-9]([0-9+]+)?[0-9]$";
    public static final String PHONE_NUMBER = "^[0-9]([0-9+]+)?[0-9]$";
    public static final String PHONE_NUMBER_8_DIGIT = "[0-9]{8}"; // "^[0-9]([0-9+]+)?[0-9]$";
    public static final String POSTAL_CODE = "[0-9]{5,6}";

    public static final String URL = "^[=/a-zA-Z.0-9_&?-]*$";

    public static final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

    // generic stuff
    public static final String ALPHA_NUMERIC = "^[a-zA-Z0-9]*$";
    public static final String ALPHA_NUMERIC_WITH_SPACE = "^[a-zA-Z 0-9]*$";
    public static final String ALPHA_NUMERIC_WITH_SPACE_AND_UNDERSCORE = "^[a-z_A-Z 0-9]*$";
    public static final String ALPHA_NUMERIC_WITH_DOT_SPACE = "^[a-zA-Z. 0-9]*$";
    public static final String ALPHABET_WITH_DOT_SPACE = "^[A-Za-z. ]*$";

    public static final String ALPHA_NUMERIC_WITH_SPACE_EMAIL = "^[a-zA-Z 0-9@.]*$";
    public static final String ALPHA_NUMERIC_WITH_SPACE_DASH_SLASH = "^[a-zA-Z 0-9\\/-]*$";
    public static final String ALPHA_NUMERIC_WITH_SPACE_DASH_SLASH_PLUS_AMPERSAND = "^[()._,\"a-zA-Z 0-9+&\\/-]*$";

    // Singapore
    public static final String NRIC_TYPE_FORMAT = "^[stST]\\d{7}[a-zA-Z]$";
    public static final String FIN_TYPE_FORMAT = "^[fgFG]\\d{7}[a-zA-Z]$";


    public static final String PHONE_NUMBER_FORMAT = "\\d{8}";

    public static final String NUMERIC = "^[0-9]*$";

    public static final String TIME = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    public static Boolean isValidSearch(String key, String value, Model model, MessageSource messageSource,
                                        Locale locale, String regEx) {
        Boolean isKeyValid = false;
        Boolean isValueValid = false;

        if (key == null || key.isEmpty())
            isKeyValid = true;

        if (value == null || value.isEmpty())
            isValueValid = true;

        if (!isKeyValid) {
            isKeyValid = key.matches(regEx);
            // key valid did not match
            if (!isKeyValid)
                model.addAttribute("msg", messageSource.getMessage("error.no.special", null, locale));
        }

        if (!isValueValid) {
            isValueValid = UtilString.minLengthCheck(value, 4);

            if (isValueValid) {
                isValueValid = value.matches(regEx);

                if (!isValueValid)
                    model.addAttribute("msg", messageSource.getMessage("error.no.special", null, locale));
            } else
                model.addAttribute("msg", messageSource.getMessage("error.min.character", null, locale));

        }

        // Special case, must find a better way to implement this

        if (!isValueValid) {
            /*
			 * if
			 * (key.equalsIgnoreCase(UtilSearchableColumn.UserManagement.EMAIL))
			 * { isValueValid =
			 * value.matches(UtilValidation.ALPHA_NUMERIC_WITH_SPACE_EMAIL);
			 * model.addAttribute("msg", ""); }
			 */
        }

        return isKeyValid && isValueValid;

		/*
		 * if (key != null && value != null && key.isEmpty() == false &&
		 * value.isEmpty() == false) { if (UtilString.minLengthCheck(value, 4)
		 * == false) { model.addAttribute("msg",
		 * messageSource.getMessage("error.min.character", null, locale));
		 * return false; }
		 * 
		 * if (key.matches(UtilValidation.ALPHA_NUMERIC_WITH_SPACE) &&
		 * value.matches(UtilValidation.ALPHA_NUMERIC_WITH_SPACE)) { return
		 * true; } else {
		 * 
		 * if(key.equalsIgnoreCase(UtilSearchableColumn.UserManagement.EMAIL)){
		 * 
		 * if(value.matches(UtilValidation.ALPHA_NUMERIC_WITH_SPACE_EMAIL)){
		 * return true; } }
		 * 
		 * model.addAttribute("msg",messageSource.getMessage("error.no.special",
		 * null, locale)); return false; } } return false;
		 */
    }

    public static Boolean isValidSearch(String keyword) {

        if (keyword == null || keyword.isEmpty())
            return true;

        return keyword.matches(ALPHA_NUMERIC_WITH_SPACE_DASH_SLASH);

    }

    public static Boolean isValidSearch(String key, String value, Model model, MessageSource messageSource,
                                        Locale locale) {

        return isValidSearch(key, value, model, messageSource, locale, UtilValidation.ALPHA_NUMERIC_WITH_SPACE);
    }

    public static Boolean isValidWithSpaceAndDash(String key, String value, Model model, MessageSource messageSource,
                                                  Locale locale) {
        return isValidSearch(key, value, model, messageSource, locale,
                             UtilValidation.ALPHA_NUMERIC_WITH_SPACE_DASH_SLASH);
    }

    public static Boolean isValidWithSpaceAndDashAndPlusAndAmpersand(String key, String value, Model model,
                                                                     MessageSource messageSource, Locale locale) {
        return isValidSearch(key, value, model, messageSource, locale,
                             UtilValidation.ALPHA_NUMERIC_WITH_SPACE_DASH_SLASH_PLUS_AMPERSAND);
    }

    public static Boolean isValidWithSpaceAndDashAndPlusAndAmpersandForAddAndUpdate(String modelKey, String key,
                                                                                    Model model,
                                                                                    MessageSource messageSource,
                                                                                    Locale locale) {
        return isValidSearch(key, "", model, messageSource, locale,
                             UtilValidation.ALPHA_NUMERIC_WITH_SPACE_DASH_SLASH_PLUS_AMPERSAND);

    }

    public static Boolean isIMEI(String str) {
        return str.matches(UtilValidation.NUMERIC) && str.length() > 0;
    }

    public static Boolean isValidId(Integer id) {

        return id != null && id > 0;
    }

    public static Boolean isValidIds(Integer... ids) {

        if (ids != null) {

            for (Integer i : ids) {

                if (!isValidId(i))
                    return false;
            }

            return true;
        }

        return false;

    }

	public static Boolean isValidListIds(List<Integer> ids) {

		if (ids != null) {

			for (Integer i : ids) {

				if (!isValidId(i))
					return false;
			}

			return true;
		}

		return false;

	}

    public static Boolean isValidString(String string) {

        return string != null && !string.isEmpty();
    }

    public static Boolean areValidStrings(String... strings) {

        if (strings != null && strings.length > 0) {

            for (String s : strings) {
                if (!isValidString(s))
                    return false;
            }
            return true;
        }

        return false;

    }

	/*
	 * public static Boolean isValidFile(MultipartFile file){
	 * 
	 * return file != null && file.getSize() > 0;
	 * 
	 * }
	 */

    public static Boolean isFileNotEmpty(MultipartFile file) {

        return file != null && file.getSize() > 0;

    }


    public static Boolean areObjectNulls(Object... os) {
        if (os == null)
            return true;

        for (Object o : os) {
            if (o == null)
                return true;
        }

        return false;
    }

}
