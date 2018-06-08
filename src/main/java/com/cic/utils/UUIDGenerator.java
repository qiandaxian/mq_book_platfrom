package com.cic.utils;

import java.lang.management.ManagementFactory;

/**
 * UUID generator utility.
 * 
 * @Project GJDD-DTD
 *          Definition)
 * @author Ryan(lqs.net@163.com)
 * @since 2013-4-24
 * @version 1.0
 * @change_log <pre>
 * 		[2013-4-24 by Ryan] Initialize class.
 *      [2013-4-25 by Ryan] 通过混淆算法生成20个字节的惟一UUID.
 * </pre>
 */
public class UUIDGenerator {
    private static final int UUID_BIN_LEN = 10;
    private static final int UUID_STR_LEN = UUID_BIN_LEN * 2;

    private static long procId = -1;
    private static long threadId = -1;

    /**
     * 生成20字节的UUID字符串,不含'-'字符.
     * 
     * TODO 未追加jvmId,ip和mac信息,需要完善.
     * 
     * @return
     */
    public static String genUuidStr() {
        StringBuilder buf = new StringBuilder();
        buf.append(java.util.UUID.randomUUID());

        buf.append(genAddon());

        for (int i = buf.length() - 1; i >= 0; i--) {
            if (buf.charAt(i) == '-')
                buf.deleteCharAt(i);
        }

        while (buf.length() > UUID_STR_LEN) {
            buf.deleteCharAt(UUID_STR_LEN);
        }

        while (buf.length() < UUID_STR_LEN) {
            buf.append('0');
        }

        threadId = Thread.currentThread().getId();
        return new String(buf);
    }

    /**
     * 根据虚拟机进程名、线程ID生成8个字节的附加串.
     * 
     * @return
     */
    private static String genAddon() {
        StringBuilder buf = new StringBuilder();
        buf.append('/');
        buf.append(threadId);

        buf.append('/');
        buf.append(procId);

        String bufStr = new String(buf);

        String addonStr = Integer.toHexString(bytesToInt(bufStr.getBytes()));
        while (addonStr.length() < 8) {
            addonStr = "0" + addonStr;
        }
        addonStr = "-" + addonStr;

        return addonStr;
    }

    private static int bytesToInt(final byte[] bytes) {
        int hVal = 0;

        if (bytes != null) {
            for (int i = 0; i < bytes.length; i++) {
                hVal += (hVal << 1) + (hVal << 24);
                hVal ^= bytes[i] & 0x0ff;
            }
        }

        return hVal;
    }

    /**
     * 将UUID字符串转成二进制数据.
     * 
     * @param uuidStr
     * @return
     */
    public static byte[] toUuidBinary(final String uuidStr) {
        if (uuidStr == null) {
            throw new RuntimeException(
                    "The parameter uuidStr is null.");
        }

        if (uuidStr.length() != UUID_STR_LEN) {
            throw new RuntimeException("The length["
                    + uuidStr.length() + "] of parameter uuidStr is not "
                    + UUID_STR_LEN);
        }

        byte[] uuidBinary = new byte[UUID_BIN_LEN];

        for (int i = 0; i < UUID_BIN_LEN; i++) {
            uuidBinary[i] = (byte) (Short.parseShort(
                    uuidStr.substring(i << 1, (i + 1) << 1), 16) & 0x0ff);
        }

        return uuidBinary;
    }

    /**
     * 将UUID二进制数据转换成字符串
     * 
     * @param uuidBinary
     * @return
     */
    public static String toUuidString(final byte[] uuidBinary) {
        if (uuidBinary == null) {
            throw new RuntimeException("parameter uuidBinary is null.");
        }

        if (uuidBinary.length != UUID_BIN_LEN) {
            throw new RuntimeException("The length[" + uuidBinary.length
                    + "] of parameter uuidBinary is not " + UUID_BIN_LEN);
        }

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < UUID_BIN_LEN; i++) {
            String str = Integer.toHexString(uuidBinary[i] & 0x0ff);

            while (str.length() < 2) {
                str = "0" + str;
            }

            buf.append(str.substring(0, 2));
        }

        return new String(buf);
    }

    static {
        try {
            // JVM name format as: pid@hostname
            final String jvmName = ManagementFactory.getRuntimeMXBean()
                    .getName();

            procId = Long.parseLong(jvmName.substring(0, jvmName.indexOf('@')));

            threadId = Thread.currentThread().getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
