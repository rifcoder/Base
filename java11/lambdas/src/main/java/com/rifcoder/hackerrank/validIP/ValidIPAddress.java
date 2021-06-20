package com.rifcoder.hackerrank.validIP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidIPAddress {

    public static boolean ensureValidIP(String address) {
        var pattern = Pattern.compile("([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})");
        Matcher matcher = pattern.matcher(address);

        boolean validIp = matcher.matches();

        if (validIp) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String group = matcher.group(i);
                int ipPart = Integer.parseInt(group);
                if (ipPart < 0 || ipPart > 255) {
                    validIp = false;
                    break;
                }
            }
        }

        return validIp;
    }
}
