package edu.icet.utility;

import edu.icet.dao.ProfilePicEntity;


import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ProfilePicUtility {


    private ProfilePicUtility() {
        throw new IllegalStateException("Utility class");
    }

    // Compress image data using Deflater
    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
            return new byte[0];
        }
        return outputStream.toByteArray();
    }

    // Decompress image data using Inflater
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
            return new byte[0];
        }
        return outputStream.toByteArray();
    }

    // Get the default profile picture entity
    public static ProfilePicEntity getDefaultProfilePicEntity()  {
        byte[] defalutPic ={120, -38, -107, 121, 117, 80, 28, -64, -45, -27, -30, -106, 96, 9, -82, 65, 66, 112, 8, -18, -110, -32, -63, 97, -105, 69, 67, -48, -59, -35, -35, 66, -80, 69, 2, 36, -72, 4, 8, -74, -72, 123, -112, -59, -125, -69, -69, -69, 47, 114, -28, -85, -17, -18, 126, 119, -9, -41, -67, -87, -102, -22, -86, -82, 121, -81, -89, -89, 106, 122, 106, -6, 105, -26, 105, 5, -128, -81, 40, -85, 32, 11, 64, 66, 2, 0, -112, -98, 7, -32, 105, 30, -16, 1, -128, -126, -4, 15, -1, 102, -108, 103, -96, -94, -93, -2, 3, 38, 58, 58, 26, 58, 22, 38, 54, 54, 22, 38, 22, 22, -10, 11, -68, 23, -40, 56, -72, 56, 88, 88, 47, 9, 94, -30, -30, -31, -29, 19, -32, 99, -65, 32, 124, 69, -120, -1, -22, -39, -58, -1, 71, -126, -12, 111, 37, 10, 42, -26, -13, 66, 124, 28, 44, 28, -4, -1, 111, 60, -75, 3, 8, 48, 1, 19, -128, 9, 20, 36, 58, 0, 50, 1, 18, 10, 1, -46, 83, 23, -128, -6, 95, -104, 104, 72, 72, -1, 2, -2, 111, 32, 33, -93, 99, 96, 98, -95, -95, -96, 98, 63, -5, 101, -16, 1, -49, -54, -56, 40, 104, -88, 104, 88, 24, 40, 104, -1, -36, 0, 100, 20, 84, 52, 2, -12, 55, 24, -124, -36, -104, -81, -92, -44, -115, -23, 28, 3, -29, -80, 94, -65, -105, -50, 38, -46, -8, 18, 20, 95, -47, 49, 122, 66, 76, -49, -93, -23, -28, 92, -55, -64, 91, 37, -109, -112, -77, 122, -6, 76, 67, -7, -52, -1, -68, -5, -1, 45, -14, -52, -125, -127, -116, -114, -118, -122, -119, -14, -20, -27, 35, 0, 32, 35, -95, 32, -95, 98, 60, 43, 32, -3, -49, 32, 80, 80, 9, -48, -48, 9, -33, 112, 63, 43, 60, 11, -116, 98, -68, 122, -97, 93, -47, -79, 74, 68, -89, -15, 90, -38, 41, -25, -28, -12, 105, 14, -16, -30, -103, 15, -103, 0, -123, 0, 32, 1, -40, 75, -95, -93, -59, 12, 15, -96, -93, 3, -124, -121, 35, -45, -3, -86, -113, 58, 28, 49, 3, 108, -119, -65, -111, 19, -36, -35, -100, 30, -75, -124, 67, -106, -13, 88, -65, -118, 50, -105, -107, -116, -6, -112, -42, -114, -19, 15, 41, 125, 50, 32, 82, 110, -97, 32, 6, -17, -104, 45, 16, 95, -105, 94, 21, 101, -32, -66, -97, 9, 55, -33, 32, 67, 9, -76, -36, 62, 38, 20, 113, -69, -86, 88, 11, -3, 84, 105, 112, -45, -25, 81, 57, 38, -74, 57, 58, -78, 91, -16, -88, 16, -122, -17, -73, -29, 67, -102, -66, -2, -48, 103, 88, -87, 110, 67, -75, -73, 53, -49, 96, 44, -44, 104, -45, -106, -30, 61, -76, 117, 93, -56, -28, 10, -4, -43, -86, 127, 116, -34, 116, -88, 100, -92, -57, -82, -12, -73, -71, 119, 77, -15, 124, -75, 32, -24, -25, -102, -94, 71, -22, 43, -72, -100, -84, 16, -90, 120, -120, -117, -14, -120, -65, 50, -27, -68, 25, 74, -52, 60, -25, 54, -89, -54, 105, 114, -108, -105, 36, -6, -82, 56, -105, 117, 49, 43, 18, 70, 124, -80, 6, -80, -67, 19, -54, -1, 94, 114, 76, 12, -13, -49, -112, 48, -51, -18, -101, -61, -48, 28, -122, -48, -126, 111, 35, 123, -62, -14, -62, -22, -53, -121, 90, -105, -20, -2, 119, 121, -47, 21, -61, -2, -41, -102, 46, 84, 3, 28, 112, -75, 34, 93, -66, -103, -27, -70, 99, 18, 17, 5, -85, -16, -117, -24, -11, -86, -110, -8, 14, 37, 104, 127, 99, 78, -73, 62, -40, 46, 107, -104, 123, 73, 70, -125, -97, 73, -24, -123, -23, -104, -68, 36, 3, 87, -128, 99, 115, 49, 62, 1, 1, -32, -29, 71, 36, 2, 124, -44, -113, -110, -1, -53, 84, 115, 91, 68, 119, -116, 15, -8, -82, -123, 97, 97, 58, 80, -55, -113, 9, -16, 55, 82, 78, -26, 16, -113, 55, 92, -72, -42, 69, -76, 44, 87, 25, 97, -64, -114, 114, -21, 70, -123, 38, 116, 91, -49, -107, 56, 24, 11, -65, -37, 50, -42, -110, -44, 29, -56, 46, 37, 8, -38, 109, -101, 115, -102, -107, -58, 42, 52, -118, -43, -36, -100, 114, 90, -8, -3, 37, 79, -48, -41, 45, 2, -29, 102, -107, 88, -37, -52, -7, -89, -94, 62, -86, -3, 30, -48, 57, -63, 124, -77, 56, -15, 5, 124, 43, -33, -127, 79, -80, 86, -49, 96, 64, -97, -79, -5, 54, -15, -84, -9, -52, -128, 33, 94, -80, -31, -40, -109, 33, 89, 8, -18, -92, 32, -60, 80, -110, 83, -119, -94, 75, 58, 50, -123, -85, -122, 14, -96, 64, -3, -108, 51, 68, 16, 27, 30, 46, -1, 81, -38, 65, 64, -46, -20, 104, -40, -96, -48, -93, 106, 22, -86, 93, -7, -112, 5, 59, -45, -102, 99, 97, 68, 63, 44, -80, -38, 114, -52, 106, -32, 1, -120, -52, 46, 55, -90, -66, 68, -86, 42, 119, -114, 2, 120, -2, 28, -124, -69, -65, 10, 88, 112, 30, 89, -52, 17, -76, -74, 89, -77, 11, 44, 118, -34, 41, -10, -52, 46, -52, -5, 30, -88, 46, 5, 28, 116, -81, 30, -81, -10, -3, 73, 46, 47, -82, 98, -99, 114, 21, 63, -51, -101, -71, -19, 76, 107, 63, -34, 103, -5, 4, 24, -3, 6, -9, 90, 34, 114, -5, -6, 107, -11, -63, -41, -1, 109, -22, -102, 28, 123, 101, 14, 83, 19, 65, -73, -109, -75, -2, -98, 80, -40, 50, 100, 120, 43, 24, -123, -116, -121, 43, -19, 12, 45, 42, 89, 94, 68, 45, 34, 86, -123, 39, -108, 18, -34, -59, -122, -92, -53, -126, -60, 84, 84, -6, -97, 89, -107, -63, 16, 20, 84, -61, 18, 115, -8, -119, -90, -104, -74, 17, -5, 83, 92, 49, -127, -119, 115, -114, 20, -101, -89, 43, 106, -59, -7, -13, 122, -14, -88, -121, 87, 46, 24, 50, 68, 101, 47, 32, -10, -63, 57, 40, 2, -87, -36, 121, 88, 9, -102, -96, 43, 86, 64, 35, -55, 19, 26, -29, 81, 6, 71, 0, -93, -110, -12, -34, -90, -102, -126, 88, 123, 7, 76, 81, 19, 94, 114, -97, 123, 98, 112, -72, -7, -19, -120, 99, 48, 54, 89, -128, -114, -17, -126, 110, -117, -20, 84, -101, -84, 112, -66, 79, 78, -38, -49, 55, -57, 62, -60, -91, -99, -43, -28, -102, 67, 37, -71, -49, 89, 29, 71, -82, 100, -41, -43, 21, 91, 6, 22, 10, -50, 125, 23, 13, -114, -5, 82, 12, 107, 106, 38, 108, -12, 114, 33, -120, -126, -23, -111, -106, 79, 100, 23, 121, 110, -127, 22, -81, 57, -55, 41, -92, -64, -123, 44, 16, 118, 96, -110, -74, 97, -34, 43, 81, -13, 124, 48, 102, 16, -58, -113, 30, 79, 79, 20, 22, 73, 33, -122, 111, 42, 29, -106, 80, 126, -118, 120, 73, -46, 62, 113, -61, -53, 116, -124, 121, -17, -11, -87, 95, 92, 40, 124, -90, 92, 35, -20, -72, -11, 92, -53, -97, -48, 31, 25, -58, -47, 41, -106, 121, -24, 99, 60, 13, 98, 7, 21, -1, 126, -84, 42, -32, -115, -4, -30, 80, 16, -105, 54, 58, -71, -64, -48, -9, 45, 6, -61, -126, 59, -38, 36, 99, -21, -126, -87, 17, 21, 127, -115, -72, 2, 54, 28, -109, -88, 91, 20, -15, 80, -18, 33, 63, 95, -115, -19, -22, -54, -62, -21, -69, -66, -50, -104, 52, 100, 102, -99, -78, -102, 84, 79, -80, 37, -42, 95, 114, -109, 56, 49, -52, 123, -11, 126, 117, -101, 100, 63, -45, -65, 56, 38, 103, -38, -113, -35, -102, -54, -71, -90, -95, 66, 65, -32, 93, -89, -9, 78, 6, 14, 67, 59, -64, -39, -26, 30, -80, 100, 3, 64, 111, 94, 120, -116, 46, 5, -85, -86, 71, -70, -65, 98, 54, 11, 73, 31, -110, -119, 1, 10, -30, 57, -65, -11, -112, -32, -86, -71, 35, 127, -56, -109, -33, -54, 93, -12, 29, 74, -27, -77, 77, 107, -118, -86, -112, 41, -28, 104, -50, -69, -28, 93, -65, 121, -5, 120, -70, 83, -89, 59, 126, -31, -64, 87, -55, 119, -3, -56, 2, -22, -57, -104, -117, 9, -21, 94, 20, -12, -50, 100, 40, 123, 2, 4, -75, -40, -74, 4, 10, 50, -37, -66, -103, 86, 76, 55, 118, 22, 49, -43, -42, 39, 123, -59, -77, 67, -9, 45, 4, 15, -38, -51, 84, 54, 11, 79, -80, -17, 124, 2, -68, 40, -76, 105, -71, 52, -12, 48, 100, 61, 112, -98, 38, 59, -127, 67, -91, 115, -53, 10, 90, 92, 106, 57, 122, 12, 82, 117, -38, -89, 29, -82, -64, -114, -41, -23, 62, -11, -124, -66, 110, 105, 120, 29, 122, -62, 85, -27, 22, 104, -57, 90, 103, 47, -71, -75, 125, -58, -75, 117, -60, -58, 87, 75, -8, 28, 80, -18, 28, -1, 38, 55, -28, -10, -54, -113, -17, -75, 58, -50, -119, -128, 69, 121, -89, -67, 12, 11, -24, -68, -23, -67, 49, -113, 101, -43, 125, -65, -75, 53, 10, -99, 6, -82, -111, 28, -113, 39, 35, 50, -30, 38, -36, -94, -63, 22, -33, -31, 14, 21, -6, 16, -69, -17, 80, 26, 55, 47, 79, 61, 54, -117, -85, 31, -53, -15, -115, 30, -99, 29, 15, -32, 14, 101, 93, -105, -38, 34, -4, -63, -39, 92, -66, -41, 68, 23, 27, -41, 68, -27, 22, -108, 67, -67, 3, -6, -72, -61, -58, 11, -108, -82, 104, -65, 102, -32, -94, 48, -63, 19, -8, 74, -45, 30, -47, 27, -112, -101, -29, -38, -49, 18, 80, -98, 48, 97, -94, 112, -28, 75, 103, -119, 108, 92, 74, -73, -56, -111, 102, -55, -93, 119, 121, 63, -26, 56, -100, -49, -62, 52, 38, 5, -47, -5, 85, -72, -24, 18, 14, -38, -124, 102, 40, 5, -113, 15, 74, 10, -106, -112, -47, 49, -59, -27, 88, 83, -7, 117, -127, 3, 5, -91, -54, 63, 127, -60, -86, 105, 98, -48, -88, 83, 56, -31, 101, -20, 115, 58, -64, -85, 57, 10, 71, -34, 119, 66, 38, -52, -11, 22, -109, -51, 121, -59, 110, 127, 7, -26, -34, -41, -43, 35, 36, -15, -22, -41, 87, 0, -20, -89, -53, 124, -66, -111, -33, -33, 28, 31, 8, -98, 12, -17, 111, -81, -36, -7, 40, 94, 43, -52, 19, 49, 35, 34, -113, 14, -113, -75, -20, 64, 70, 68, -70, -89, -68, -83, -80, -14, 125, 86, 81, -48, -112, -128, 99, 68, 109, -110, 65, 112, -118, -61, 22, -63, 36, 12, -78, 8, -93, -90, 13, 0, -63, 40, 85, 112, -123, 75, -47, -43, 19, -123, 4, -107, -115, 115, 125, 67, -42, 53, 88, -44, 61, 63, -35, -47, -27, -90, 14, 1, -77, 6, -1, -5, -90, -89, -91, -67, -40, 43, -68, -44, 46, 75, 22, 66, 113, 67, 76, -68, 39, 67, -105, 28, 81, 10, 3, -20, -17, -44, -100, -57, -120, -70, -69, 82, -20, -15, -102, 71, 20, 111, 22, -38, 125, 41, 43, -117, 94, 94, -95, 92, -23, 38, -69, 127, 2, 32, 31, 55, -115, 120, -125, -39, 46, 69, -92, 28, -3, -108, -7, 62, -71, -124, -3, 44, -39, 104, -46, -82, -30, -29, -111, 117, -40, -45, -127, -58, -99, 120, 55, -117, 74, 46, -37, -101, -106, 26, 0, -42, 97, -113, -44, 17, -99, -77, 98, 63, -93, 77, -53, -114, -119, 77, 30, 42, 124, -59, -54, -86, 10, 93, -82, -99, 97, -75, 5, 16, 74, -45, 3, 91, -102, 91, -119, 74, 68, 16, 79, -68, 66, -97, -112, -56, -111, -22, -71, 54, 82, -108, 125, -38, 111, 42, -18, 80, -111, -49, 80, 70, -13, -49, -80, 74, -21, 50, -21, 82, -92, -4, -115, -79, 100, -22, -73, -38, 114, -68, 117, -65, 105, -21, -26, -104, 37, -102, -23, 4, -63, -105, -107, -37, 18, -33, 125, 11, -120, 91, -122, -82, 42, 1, 59, 60, -11, 51, 25, 29, 94, -78, -20, -36, -77, -80, 110, -67, -123, 51, -83, 61, -35, -2, -127, 73, 89, 31, -91, -68, -93, 47, -30, 71, 91, -104, -48, 46, -60, 20, -47, -95, -33, 52, 53, 38, 72, 57, -51, 29, -60, -62, 20, 43, -38, 52, 104, -78, 37, 61, 31, -125, -95, -5, -13, -53, 74, 38, -34, -63, 106, 96, 88, -78, -30, -36, -113, 19, -2, -85, -57, 98, 2, -49, -121, -100, -29, -108, -13, -70, 26, -26, 63, 36, 75, 71, -103, -72, -32, -29, -11, 61, -24, 7, 111, -18, 104, -123, 25, 68, 127, -103, -24, -23, 98, 7, 103, 46, -89, 93, -16, 1, 84, -107, 81, -24, -92, -29, -109, 103, 62, -111, 82, -110, 110, -70, 16, 125, 39, 10, 61, 13, -93, 102, -102, 124, -113, -97, 21, -9, -58, 15, -26, 88, 63, -31, 84, -77, -105, 70, 55, 19, -121, 28, 18, -66, -83, 3, -83, -58, 122, -45, 74, 108, -75, -25, -53, 92, -41, -27, -124, 72, -18, 91, 119, 52, -5, -101, -107, 77, -14, 84, -38, 120, 38, 12, -58, -47, -121, -72, -42, -127, 104, 5, -39, -44, -118, -50, 83, 78, -17, 22, 84, 18, 14, -8, 84, 107, 73, 112, -26, -69, -4, 112, 65, -6, -106, -123, -120, -127, -31, -8, -83, 102, 117, -4, -9, -37, 93, 83, 79, -128, -119, -18, -116, 122, 23, -55, 102, 60, -110, -113, -35, -71, 62, 104, -85, -109, -55, 17, 62, 82, 95, -107, -10, -80, -5, 27, -67, 56, 11, 85, 117, -50, -27, -73, 106, -27, -50, -100, 86, -45, -122, 113, 1, -108, -23, 39, 94, 28, 48, -112, 70, 109, 124, -91, -82, -126, 46, 67, 116, -63, -9, -81, 47, -50, -71, 110, 104, -72, -125, -125, 79, -11, 17, 43, -30, -95, -113, 127, -82, 74, 39, -67, 56, -72, -12, -48, 51, 10, 26, 42, -45, 68, -100, -10, 76, 24, -60, 122, 124, 79, 7, -56, -128, 28, 29, -6, -102, -59, -98, 42, 115, 109, -92, -57, 28, 84, -57, 19, -93, 45, 57, -13, 90, 5, 16, -111, 125, -69, -105, 29, -9, 73, -50, 27, -74, -27, 38, -78, -92, -19, 40, -70, 35, -28, 24, -110, -56, 121, -93, 24, 92, 21, -99, -76, 7, 114, 41, -91, 13, -12, 34, -95, -44, 66, 123, 45, 34, -125, 103, -75, -86, -102, 31, -83, -121, 84, 59, 69, -10, -19, -55, -67, -14, 22, -54, -38, 127, -25, -57, -63, 27, -118, 62, 35, -54, -20, 127, -100, 56, 44, 120, 127, -61, 112, -87, 119, -18, 74, -105, -24, 42, -58, -31, -28, 127, 46, 95, -48, 23, 69, -65, 60, 31, -49, 82, 62, -7, 38, -29, -47, 112, -55, 118, -7, 36, 69, -74, -14, -9, -114, 81, -75, -6, -29, -87, -17, -75, -7, 125, -101, 74, 94, -94, -80, -46, -31, 67, -117, -111, -42, -17, 28, -32, -59, 21, -116, -127, 17, -70, 71, 47, -73, 95, 98, 56, -82, -31, -2, -84, 70, 8, 81, 73, -89, -42, 103, 85, -88, -5, -41, 89, 120, -42, 31, 76, -115, 42, -2, -79, 90, 106, 26, 18, 56, -13, 75, -73, -112, 10, 60, 101, -87, -79, -73, -116, -122, -104, 77, 55, -38, 37, 104, -52, -28, 109, 23, 20, -38, 29, -72, -49, 127, 91, -73, -32, -18, -20, 104, -33, -96, 113, -86, -41, -106, 31, -113, 92, 76, 126, 63, 19, -87, 1, -42, 15, 122, -89, 17, 38, -11, -81, -84, 123, -79, 124, -128, -58, 35, -25, -114, 20, 75, 124, -13, -38, -118, -108, -124, 40, 14, 92, 2, 105, 120, -28, -36, -107, -79, 28, -48, -63, -23, 25, -64, 39, -64, -85, -115, 17, 21, 126, -112, 117, -122, 46, -106, -32, -116, 103, -87, -44, 33, 1, 108, -71, -120, 47, -73, -28, 123, -4, -71, -102, -50, -73, -48, 102, -40, -20, 37, -58, -3, -121, -69, 38, -98, -111, -102, 37, -21, -106, -39, 69, -42, 75, -21, -103, -15, -28, 14, -22, -9, 71, -9, 103, 124, -121, -65, 38, -3, 63, 85, 57, 125, -123, 115, -32, 76, -73, -9, 36, -77, 70, -5, -18, -83, 107, -70, 55, 78, -76, -26, -64, 96, -102, 108, 103, 77, -59, 11, -2, 14, -44, 0, 27, 0, -64, -122, 100, -117, -70, 48, -82, 28, 53, -89, -7, 13, 17, 90, 19, 81, 72, -61, -21, -110, -44, -124, -127, -83, 63, 105, -17, 52, 51, -22, 53, 122, 101, -39, 15, -59, -66, -108, -76, -99, -32, -23, 116, -38, -27, -5, 126, -68, -48, -70, -104, 6, -12, -16, -58, -109, 17, 105, -55, -126, -68, 83, 105, 68, -96, 89, 122, -95, 121, -20, 103, -70, 46, -91, -15, -45, 60, 66, -90, 57, -75, 26, 99, 45, -83, 42, 49, -60, -68, -48, 84, 32, -29, 88, -21, 80, -65, 28, 74, 125, -24, -4, -10, -59, -41, -96, -95, -82, -72, 105, -97, 39, -128, -47, -112, 39, 116, -50, 21, -94, -92, -105, -2, -74, -98, 77, -126, -73, 5, 79, -59, -101, 63, -101, -44, 82, 107, -81, -96, -69, -74, 57, 115, -61, 99, 83, 49, 79, -85, 56, -40, -1, 57, 69, -79, -71, -123, -9, -81, -117, 124, -44, -126, 102, 39, 78, -50, -128, 50, 92, -13, -51, 81, -9, 14, -2, 113, -70, -103, 114, 121, -5, 38, -121, 9, 93, -74, -118, 115, 10, 104, -12, -43, 121, 109, -17, -109, -16, -57, 121, -13, -93, -93, 59, -60, -65, 36, 25, -51, -72, -57, -49, -89, 107, 83, -101, -28, -57, 82, -121, 14, -35, -82, -33, -78, 10, 104, -112, -8, -55, -90, 23, 8, 14, 0, -50, 16, 31, 36, -90, -49, -11, -128, 112, 96, 34, -107, -7, 18, -77, -123, -105, -93, -47, 65, 76, -103, -51, -69, 63, -21, 22, -84, -61, -86, 43, 33, -53, 125, 57, 3, 100, -20, 123, -39, 88, -89, 84, -40, -100, 6, 31, 79, 59, 113, -42, -43, 73, 60, -55, -26, 29, 71, 125, 99, 87, 60, -52, 104, -88, -111, 57, -47, -10, -37, 99, -104, 85, 59, -93, -11, -5, -123, 111, 123, 43, -106, -52, 76, -7, 108, -104, 102, -125, 76, -62, 42, -43, 68, 121, -82, 84, 51, 43, -35, 22, 74, 23, 34, -52, -107, 41, 76, -93, 59, 6, 61, 112, 33, 24, 95, -80, -73, 14, 125, 69, -19, 57, 76, -93, -74, 98, -44, 116, -81, 16, 61, 79, -128, -23, -94, -7, -42, 65, 125, -71, 84, -48, 14, 28, -78, 95, 35, -25, -19, 86, 46, -78, 55, 86, -97, 105, -91, 83, -114, -61, 112, 83, 13, -115, -111, 11, 42, 116, 27, 80, 0, 38, 78, 110, 50, -122, -1, -51, -12, -33, -88, -87, 21, 1, -70, 107, 25, -28, -55, 33, -78, 7, -102, 78, -52, -69, -76, 54, 46, -75, -39, -33, 45, 6, -47, -47, 98, 71, -31, 107, -53, -103, -55, -9, 45, -69, -66, 32, 110, 35, -88, -7, -64, 107, 23, -43, -72, -57, 68, -100, -84, -47, -18, -99, -33, 36, -24, 2, -21, 52, -127, 92, 112, 58, 96, 44, 85, 107, 31, 39, 89, -76, 66, -14, 93, -30, 21, -113, -36, 91, 16, -55, -11, 105, -15, 62, 125, 34, -91, -15, -121, -58, -2, -15, 56, 50, -87, -54, 61, -57, -120, -39, 101, -51, -73, 84, 81, -75, -76, 0, -56, 5, 71, 44, -115, 55, 113, -83, -121, 9, -10, -62, 76, 36, -51, -18, 1, 85, -89, -66, -107, 68, -4, 74, -85, 39, 11, 123, 85, -53, 99, -50, 19, 96, -95, -32, 15, 14, 53, 103, 36, -30, -27, -41, 15, 43, -31, -4, 50, -115, 108, 23, 41, -34, 88, -83, 78, 4, 72, -53, 44, -46, 44, 102, 31, 71, -30, 119, 24, 125, -20, -24, 81, -85, 99, 29, 79, -64, 83, 112, 29, 14, 98, 47, -14, -63, 115, -9, -119, 3, 71, -38, -19, -71, -10, -65, -21, 55, 37, 4, 1, 42, 8, -74, -44, 63, -120, 121, -117, -84, -92, -98, -21, -43, -117, 95, 3, -104, -30, -36, -11, 27, -9, 105, -2, -73, 104, -22, -34, 58, -93, -23, 67, 33, -100, -69, 114, -47, 2, -120, -118, -119, 7, 106, 28, -117, 89, 96, 121, 105, -39, -74, 9, 80, -1, 109, -101, 61, 53, -59, -64, -92, 105, -53, -16, 3, -111, 98, 102, 80, -84, 15, 40, -3, 60, 97, -60, -102, 10, 53, -121, -114, -112, 72, -24, 111, 80, -95, 102, -91, 51, -19, -103, 43, -113, -48, 111, -46, 19, -15, -82, 82, 6, -81, -49, -48, -115, 10, -39, -68, -46, 20, -67, -32, -35, 84, -6, 121, 74, 91, 107, -91, 15, 45, 9, -47, -25, 80, -49, 123, 98, -3, 9, 77, -51, 14, -109, -30, -116, 124, -55, -34, 53, 104, 90, -62, 22, 119, 63, 70, -95, 8, -34, -5, 114, -105, -54, 86, -120, 17, -49, -75, -9, -126, -66, -29, -72, -85, 51, -126, 126, 52, -35, -22, -46, 85, -72, 98, -16, 75, -99, 73, 70, -85, -89, -63, -64, -98, -106, -31, -108, -21, -92, -30, 112, 76, -126, -94, 78, -126, -99, 6, 28, -56, -107, 112, -20, 47, -115, 33, -96, -1, 78, 82, 126, -73, -110, -11, -14, 53, 14, 102, -8, -81, -32, 117, -75, 97, -113, 52, -18, -6, 5, -42, -28, -96, -58, 30, -27, -105, -35, 56, 50, 29, 99, -60, -93, -117, -108, -20, 53, -63, 34, -10, -55, 31, -3, 48, -46, -4, -54, 13, -47, -59, -4, -47, -42, 12, 76, 126, 126, -65, 68, -1, -47, -44, 22, -4, 74, 82, -41, -3, -104, -12, -56, -51, -28, 50, 24, 36, 87, 77, -52, -14, -8, 39, -113, 35, -51, -52, 29, 38, -25, -98, 101, -40, 68, -79, -25, -22, -59, -13, 117, -31, -122, 39, 47, -82, 94, 126, -86, -91, -98, -35, 1, -107, -122, -85, -75, 104, 104, -107, -65, 0, -86, 99, -119, -61, 126, -57, -116, -126, -72, 3, 18, 58, -89, -33, 74, 55, 39, -66, -114, -20, -91, -93, -117, 37, 2, -92, 37, -86, -17, 104, 51, -103, 39, -100, 74, -109, -3, 22, -53, -73, -53, 52, 90, 51, -39, 69, 57, -101, 119, 125, -83, 70, -112, 1, -27, 112, 9, 121, 28, -104, 63, -45, 41, 52, 108, -74, 46, 34, 74, 3, 78, 56, 7, -41, 77, -103, 64, 105, 118, 66, -93, 37, 78, -127, -64, 77, -93, 70, 118, -58, 15, 42, -71, 104, -9, -96, 84, -80, -1, -5, -112, -55, -94, -58, -23, 73, -13, 59, -45, -57, 108, 2, -82, 38, 30, 24, 102, 1, -75, 56, 54, 46, 79, -83, -106, -23, 103, -50, 81, -101, 6, 109, 93, 24, -2, -62, -69, 12, -44, 17, -111, 31, 22, -71, 95, -91, -15, 126, -48, -45, -81, -75, -119, -117, -10, 59, 69, -35, -95, -99, -42, -25, 42, -18, -91, -3, -98, 4, 71, 37, -111, 31, 88, -52, 45, -75, 40, 89, -59, -107, 42, -84, -87, 123, -86, 22, -26, 10, -20, 30, -54, 125, -8, -67, 19, 106, -16, -26, 101, 92, -3, 43, -23, -11, 67, 89, -1, -16, 51, 113, -15, -5, -64, 125, -108, -52, 125, -77, 112, 15, 119, 52, -83, -23, -66, -18, -41, 15, 19, -47, -7, 46, 1, 25, 49, -99, 115, 92, 126, -2, 65, 125, -22, -117, 44, -52, 15, -111, 121, -88, 113, 113, 83, -14, -13, -125, -99, 103, 59, -51, -55, -75, -70, -20, 84, 56, -96, 66, 11, -37, -12, -18, -80, -86, -47, -116, -32, 41, 111, -118, 34, -48, 116, 117, -124, 123, -30, 105, -21, -115, -88, -53, 122, 65, -41, -91, -70, -97, 60, 44, 105, 23, 126, -19, 90, -98, -80, -95, -113, 116, 15, 26, -67, -84, 46, 33, -13, -3, -60, 78, 115, -124, -73, 85, 99, -61, 99, -74, 104, 29, -122, 87, 23, -108, -93, 64, 31, 26, 6, 54, -75, -42, -23, 73, 22, 26, 22, 4, -32, 22, 92, -105, -119, 23, -88, -51, 103, -95, -66, -1, -64, -45, -100, 75, 70, 35, 30, 49, 60, -27, -18, 85, -124, 16, 61, 109, -66, 118, -120, 95, 54, 46, 48, 57, -60, 45, -24, 118, -13, 98, 12, 113, 85, -106, 13, -3, -66, -12, 122, 35, 103, 25, 104, -120, -117, 94, -91, 91, -101, -98, 15, 106, 11, 113, 62, 90, 101, 118, -36, -3, -45, -73, 97, 93, 106, 114, -21, 101, 108, -105, 45, -104, 21, 114, 78, 66, 93, -39, 118, -34, 54, -66, 121, 46, 82, -84, 101, -121, 31, 4, -80, 91, 106, 45, 12, 61, -96, -30, 84, -82, 27, 99, 55, -122, 88, 83, 74, -77, 62, -38, -105, 86, -63, -17, -110, 114, 29, -117, 22, -6, -113, -74, -64, 105, -103, -9, -55, -97, 101, 68, -34, 93, 115, -8, -87, -96, 35, -45, 94, 106, 8, 57, -81, -121, -12, 118, -32, -100, 76, 6, -11, 117, -90, 70, -9, -56, 117, -8, 61, 84, 69, 79, -81, -62, -10, 58, -113, 105, -90, -121, -116, -43, -19, -69, 115, -9, 21, -22, -5, 120, 44, 16, -28, 75, 47, 85, -15, -84, 108, 56, -4, 5, 54, 34, 102, 11, 83, -16, -44, -90, -102, -61, 30, 73, -11, 48, 107, 36, 42, -94, 33, 49, -105, -59, -20, 105, 75, -67, -123, 49, -97, -59, -116, 112, 40, -3, 9, -127, 51, -100, 98, -117, -29, -41, 32, 62, -26, 75, -123, -78, -73, 70, 87, -50, -82, -20, -69, 67, 23, -35, 18, -68, 65, 116, 28, 66, 125, 56, 91, 39, -98, 10, 15, -81, 16, -119, 22, -39, 90, -21, -76, 49, 63, 49, 63, -128, 117, 120, 53, 35, 2, 59, -65, -111, 71, -119, -6, -17, 17, 62, 62, -76, 5, -43, 116, -120, -7, 104, 31, -61, -55, -127, -93, -89, 22, -22, -46, -93, 85, 5, 17, 82, 119, 35, 61, -21, 55, -15, 37, 1, -62, -57, 71, -122, 65, -29, -26, 5, 102, -78, 74, -7, 4, 119, 116, 1, 19, -76, -85, -82, 64, -50, 28, -115, -69, 80, 50, 38, -63, -27, -123, -128, 75, -46, 39, -64, 65, 49, -109, -111, -93, -77, -30, -55, 19, 32, 37, -66, 8, 4, 90, 20, 114, -102, -9, 21, 105, -87, -35, -50, -114, 91, -92, -89, 22, 65, -24, 44, -16, -122, 38, 30, 62, -76, -124, 74, 120, 58, -78, -67, 49, 51, 113, -12, -43, 92, 84, -117, 49, 122, 24, 108, 105, 40, 119, 63, -21, 79, -90, 62, 76, -33, -88, 55, -111, 3, 9, -82, -32, 69, 76, -118, 41, -64, -121, -33, 24, 25, -34, 28, 94, 86, -52, -20, 66, 103, 35, 66, 3, 20, 118, 12, -72, -81, 26, 90, -64, -3, 98, 68, -127, 70, 14, 101, 77, -97, -84, 56, 38, 47, -75, -57, -46, 7, -10, -14, 93, 96, -73, -11, -119, -7, 11, -121, 53, -126, 3, -60, 23, -13, -121, -10, -24, -101, -42, -27, -109, -26, 85, -114, -22, 28, 93, 85, 59, 118, 53, -64, -46, -79, -22, -113, 17, -117, -86, 102, -7, -12, -9, -13, 33, 108, 15, 71, 104, -98, -38, -6, -96, -43, 82, 124, -79, -57, -38, -22, 38, 95, -60, -49, -90, -29, 123, 79, 93, 75, -46, -47, 42, -35, 41, 68, 0, 118, -119, 43, -4, -52, -64, -26, 106, -22, 76, -97, 30, -77, -116, -33, 77, 109, -58, 91, -26, 84, 43, -31, -42, 43, 21, -47, 14, 99, -36, -79, 53, -128, -79, -108, 52, -38, 61, -78, 22, -51, -38, -92, -28, 13, -38, -14, 14, 82, -115, -101, -123, -62, -36, 121, 102, 70, 39, 15, -25, -123, 22, 45, 114, -85, -100, -72, -57, 69, -48, -6, 95, -78, -106, -72, 26, -122, 53, -31, 92, 65, -37, -90, 116, 57, -112, 121, -16, -4, 63, 29, -68, 34, -93, 77, 18, -92, 49, 27, 98, -116, -98, 109, -121, -102, 122, -78, -75, -104, 30, 22, -62, -11, 36, 95, -42, -125, 116, -20, -125, -77, 93, -1, 76, -111, -81, -119, 93, 28, -123, -5, 97, -61, -26, -12, 112, 90, -68, -18, 64, 67, 112, 118, -58, 24, -42, 56, -13, -125, -51, 73, 88, -88, 25, -59, -69, -126, -112, 101, 81, 113, -13, 39, 64, 43, -23, -81, -66, 36, -46, -59, -29, -49, 44, 100, 47, -40, -98, 0, -72, -92, 51, -2, 50, 89, 104, 121, -43, -23, -83, -82, -43, -74, -56, 109, 95, -7, -79, 22, -41, 118, -84, -74, -66, 74, -96, 12, -73, 125, 76, 111, 115, 21, 73, 29, -64, -64, 41, 101, 115, 79, 61, -118, 111, -10, 9, 49, -71, 46, -123, 28, 108, 62, 1, -40, 56, 121, 109, -84, 45, -39, 78, -121, 110, 121, -100, 54, -62, -104, 91, -66, 101, -57, 72, -70, 117, -90, -32, 14, -48, 56, -58, -108, -87, -18, -60, 80, 25, -70, -6, -6, -78, 75, -26, 103, -35, 53, 120, 67, -32, -36, -113, 103, 57, -126, 29, 47, 25, 60, -38, -2, 102, -35, -80, -125, 26, -35, -10, -41, 14, -11, 12, -88, -54, 84, 119, -107, 28, -124, 58, 91, 75, -43, 69, -125, -41, 118, -125, -59, 109, 116, 6, -12, 29, -49, 35, 23, 100, -77, 82, -2, 22, -13, -27, -55, -56, -44, -26, 38, 55, 103, 7, 97, 66, 86, -4, 89, 59, -25, 40, -23, -83, -107, 2, -100, 117, 125, 115, -36, 45, -49, 112, -22, -58, 120, -11, -102, 112, -6, 65, -87, 125, -126, 10, -30, -83, -86, 76, -96, -94, 125, -30, -21, 43, 109, -101, 83, 54, 101, -63, -74, 87, 124, 99, 87, 95, 87, 125, 54, -36, -89, -55, -17, -20, -81, 127, 51, 72, -100, 108, 25, -90, 75, -112, -33, -128, -75, 36, -95, 96, 77, -82, -52, -13, -84, 12, -50, -37, 114, 118, -23, 101, 82, 73, -43, -104, 71, -85, -103, -80, 89, 43, -18, -19, -58, 123, 9, 70, -112, 88, -28, 120, 94, 95, -97, 71, -39, 7, -105, 66, -113, -81, 89, -84, 18, -121, 53, 45, -95, 93, 23, -112, -6, -113, -45, 62, 105, 43, -96, 10, 5, -89, -70, 5, -127, -70, -123, 66, -106, -74, -103, -12, 74, -68, -10, 82, 34, 90, -126, 32, 126, -83, 107, -11, -19, 31, -78, 35, 114, -46, 24, 50, 3, 97, -13, 11, -16, -11, -4, -52, 24, 92, -122, 43, -30, -59, 123, 34, -35, -109, -66, -42, 95, -60, -15, -44, -45, -68, -127, 35, -105, -22, 67, 9, -85, -11, -3, 99, 3, -44, -83, 83, 115, -42, -79, 96, 62, -27, -98, 12, -63, 43, -101, -73, 38, 12, -76, -2, -43, -9, 85, 39, 96, 35, 57, -73, -28, -17, -14, -3, -20, -18, 86, -58, -47, 93, 40, 14, -39, 16, -76, -42, -54, 15, -23, 15, -121, -12, -93, 121, -18, 64, 17, 99, -120, 35, 35, -10, -87, -125, -81, -49, -24, -104, -31, -127, -19, 102, -77, -2, 78, 13, 111, -93, 16, -113, -124, 41, -81, -19, 68, 89, -73, -34, -24, 79, -118, -115, 39, 0, 26, 127, 83, 77, -19, -91, 125, -40, -74, 69, 105, 88, 101, 76, -106, 57, 115, -74, 63, -113, -63, 110, 38, -9, 26, -18, 74, 16, 46, -105, 54, -33, 124, 67, -16, -35, -94, 109, -60, 47, -85, 15, 94, -100, -52, 41, 25, 27, -39, 2, 88, 108, 60, 92, 87, -62, -114, -28, 8, -83, 106, -86, 72, -56, 19, -32, -92, -58, -114, 112, -62, -80, -25, -11, 112, -86, -90, 85, 19, -108, -77, -110, -123, 2, -41, 127, -108, -126, -36, 31, 31, -72, -56, -126, -95, 22, -97, -72, 9, 108, -5, -34, 90, -46, 90, -67, -39, 4, 81, -112, 88, -89, -55, -63, 12, -81, -73, -32, 98, -111, 26, 118, -45, -16, 98, -111, -6, -61, 101, -91, 106, -123, 35, -107, -75, -58, 70, -14, -122, -128, 96, -63, -110, 116, 109, -3, 58, 3, -34, 0, -69, -27, -123, -33, -80, 73, 83, 114, -105, 15, 47, -31, 88, -118, 0, 33, -45, 83, -69, -42, 110, -65, -66, -123, -92, 106, -125, -36, -33, -12, 12, 69, -40, 32, -5, -37, 86, -32, 69, 76, -120, -39, 23, 65, -86, -51, -109, -22, -83, 116, -109, 106, -98, 0, -102, 80, -82, -82, -11, 71, -74, 97, -47, -118, -86, 5, -3, -43, -52, -47, -2, 6, 126, 111, -115, -81, 59, 113, -91, -22, 39, 65, -112, 27, -89, -37, -49, -7, 53, -117, -62, -10, -32, 68, 108, -21, -88, 57, 107, -8, 121, 124, -25, -70, 42, -119, 81, 110, -50, -29, 32, -92, 79, -57, -34, -71, 92, 24, -20, 70, 23, 38, 96, -121, 43, -20, -2, 85, -120, 108, 71, -84, 12, -65, 38, 3, 108, -98, 1, 102, -54, 0, 13, -79, -63, 56, -29, 21, -88, 6, -73, -21, 47, -99, -65, 45, 36, -84, -7, -128, -90, 86, -115, -12, 92, -21, -75, 12, -20, -76, -15, 50, 96, 48, -48, -112, 40, 12, -95, -22, 29, -93, 96, -124, -101, 78, -28, 90, -72, -89, 93, 35, 124, 84, 3, -22, -52, 118, -91, 27, 28, -85, 110, 20, 54, 104, -52, 83, 118, -36, -11, -75, -79, -53, -109, 78, -100, -90, -6, -15, 81, 6, 53, -28, -93, -92, -20, 35, 106, -2, -93, -107, 58, 102, 7, -23, -91, 70, 120, 72, -56, 127, 125, 56, -87, 79, 92, 104, -28, -34, 48, -45, 54, 9, 122, 118, 121, 47, -29, 30, -27, -64, -62, -28, -76, -44, 84, -4, -9, 45, 13, -113, -65, -104, -33, 52, -76, -91, -100, 8, -117, -84, -42, -49, -38, 106, 87, -34, 90, 79, 76, -68, -82, 27, -32, -45, 52, -53, 41, -114, 18, 103, -58, 93, 104, 58, -102, -58, 67, -58, -107, -94, 70, 101, 103, 38, -21, -63, -102, -71, -92, -74, -90, 63, -47, -44, 3, -51, 21, 29, -111, -117, -14, -99, 117, 122, 126, 59, -54, -77, 92, 110, 40, -38, -36, -20, 61, -61, -75, -125, 79, -106, -24, -119, -121, -106, 13, -88, 104, -28, -126, -73, 72, 61, 14, -4, 19, -76, -89, 107, -3, 4, -50, -94, -67, -104, -103, -13, -85, -26, -102, -68, 99, 18, -81, -104, 106, 26, 26, -7, 87, 127, 27, -111, 65, 26, -127, 74, -126, 127, -73, 26, -70, -96, -99, 56, 102, 83, -4, 43, -60, -119, 118, -15, -85, 121, 57, 51, 100, -67, 91, -19, -125, -48, 0, 48, 35, -60, 41, 33, -43, -55, 103, -74, 78, -102, -123, 91, 29, -54, -8, -4, -92, 8, -90, -61, -109, 46, 92, -2, 91, 84, -86, 25, -4, 29, 35, -76, -92, 122, 83, -68, 100, -20, 90, 76, 68, 75, 119, 10, -92, -39, -54, 117, -58, -77, -40, 12, 53, 107, -9, 72, -86, -57, -64, 83, 107, 96, -97, -125, 109, -21, -39, 36, 77, -26, -80, -112, 26, 103, -112, 109, 23, -106, -124, 92, 90, -81, -117, -115, -109, -5, -62, -37, 110, -47, 102, 52, 6, 19, -38, -76, 7, -113, 127, 45, -121, -128, 40, 112, -37, 42, 29, -3, -62, -104, 20, 76, -37, -58, -7, -57, 65, -3, -4, 121, -80, -56, 12, -39, -19, 49, -99, -127, -16, -45, -83, -48, -115, -121, 44, 18, 107, -117, 66, -83, 92, 8, 112, 0, 97, -15, 117, -83, -59, 15, -82, -23, -100, -81, 89, -22, -83, 120, 121, 30, 100, -3, 55, 57, 46, -113, 126, 82, 43, 23, -74, -87, 120, -61, 83, 110, -112, 77, -16, -102, -16, -1, -3, 64, -107, 70, -2, -38, 52, -37, 35, 92, 26, 112, 96, -36, 9, -66, 108, 123, 5, -79, -90, 81, 49, 64, 99, -123, -121, 45, -11, -95, -34, -9, 90, 62, 104, -108, -65, 120, 125, 62, 61, -39, 41, -93, 56, -20, -103, -17, 17, 29, 44, -30, 76, -77, 120, 124, 102, -96, 115, -64, -34, 17, 126, -95, 18, 17, -15, 4, 0, 127, -94, 16, -45, 67, 50, 118, 118, -112, 63, -65, -119, 87, -19, -10, 41, 86, 10, -61, -118, 24, -32, -49, 88, 86, -20, -57, 22, -76, 92, -75, 124, -7, 45, 48, 11, 105, -125, 100, 95, -85, -6, -77, 9, 75, 30, 92, 60, -16, -65, -28, -119, 82, -69, 91, 73, 33, -61, -36, 39, 87, -41, -99, -47, 32, 2, 94, -110, 20, 19, 18, -81, -47, -28, -15, 79, 120, -68, -102, 85, 23, -21, 30, -42, -16, 64, -10, -114, 94, 6, -42, 62, 89, 1, 21, 125, 57, -98, 96, 31, -123, -43, -83, -126, 85, -39, -122, 102, -121, 115, -99, -79, 107, -112, 30, -69, 84, 24, -120, -117, -6, -56, -29, 14, 46, -6, 49, 48, 73, -2, -49, 116, -99, -96, -96, 32, 45, -66, 100, 29, -119, 30, -62, -54, 77, 35, -78, -6, 109, 60, 84, 109, 87, -112, 121, 117, -70, -47, -117, -60, 79, 37, -18, -56, -26, -53, -70, 106, -4, -74, -70, -30, 122, 0, -110, -101, 1, 69, 122, 21, -75, 87, 77, -103, -89, -2, -9, -30, -36, -46, 58, -32, -124, -98, -99, -117, -13, -24, -108, -6, -32, -99, 87, 10, 100, -58, 36, 9, -21, -121, -110, 17, -59, -52, -127, 16, -52, 58, 16, -107, 32, 86, 106, -104, -124, -125, 112, 73, -82, -81, 116, 124, 43, 60, -110, -10, -1, 74, -95, 90, 64, -30, 121, -36, 13, -23, -16, 94, -50, 9, 70, 102, 83, -73, 97, -13, -101, 85, -125, -13, 8, -81, -116, -92, 105, -5, 92, -97, 114, 97, 21, 80, 44, 46, -91, -113, -84, 63, -81, 109, -62, 126, 76, -127, -27, -9, 58, -96, -4, -5, 2, -109, 37, -108, 105, 71, 86, -99, 113, -125, 98, 102, -84, -100, -64, 33, 55, -3, 101, 83, -98, -65, -105, 109, -21, -103, -61, 6, -114, 21, 115, -52, 54, -64, 70, 111, -19, -41, -58, 113, 82, -92, -97, 1, -98, 104, -48, 27, -126, 14, 4, 8, -85, 58, -102, -81, 49, 3, -3, -63, -15, 0, 29, 2, 32, 99, -62, 97, 90, 117, 100, -72, -50, 80, 45, -46, 97, -4, -113, 83, 125, -99, 116, -105, 73, -12, 91, -84, 90, -28, 85, 71, -113, 101, -111, -32, -49, 63, 119, 86, 94, -54, -18, -88, -12, -5, 46, -95, 67, 17, 20, 27, -74, 118, -28, -45, 9, 1, -3, 1, -93, -3, 125, 84, 1, 43, 40, -44, -110, 47, -99, 5, -112, 52, -12, 18, -103, -21, -101, 115, -56, -5, -38, 113, 66, 91, 87, 45, -21, 39, -26, 56, -82, -18, 82, -80, -17, -56, 50, -107, 65, 10, -38, 100, 17, 86, -98, 63, 28, 110, -106, -32, -58, 67, 14, -53, -76, -47, -55, 29, 110, 56, -43, -57, 38, -66, 40, 115, 116, -15, 74, 11, 42, 118, 25, 83, 35, 61, 100, -54, 94, 5, -31, 4, 34, 122, 81, 50, -38, -92, 112, -97, 65, 27, -86, -40, 43, -37, -32, -127, -92, -90, -92, 102, -125, -107, 100, -62, 27, -110, -33, -72, -23, 107, -78, -23, -15, -95, 99, 66, 67, -65, 96, -31, 63, -33, 21, 7, 32, 70, 84, 3, -88, 106, -79, 32, -101, -34, 82, -108, 67, 84, 36, 111, 121, 52, -60, 109, 63, 87, -116, 74, 49, -2, 10, -82, -5, 8, -114, 85, -92, 123, -13, -26, 57, -67, 50, -104, 88, 50, 88, -119, 12, -124, -123, -34, 13, -70, 88, -89, 100, 106, 127, 110, 25, 108, -72, 122, 76, 19, 55, -48, 43, 2, -22, 19, 54, 0, -89, -32, -7, -128, -59, -124, -102, 105, 18, 14, 53, 85, -66, -102, -109, -7, -37, 64, -17, 37, 60, 90, -102, 95, -78, -16, 7, 90, 68, -17, 78, -59, -9, -17, 10, 74, -94, 81, -18, -99, -72, 68, 123, 118, -52, -53, -123, -91, 73, 94, 100, -103, 47, -54, 54, 57, -83, -27, 57, -106, 125, 60, -15, 4, 92, -88, 4, 88, 75, 12, 82, -67, 5, 116, 110, 68, 62, 7, 65, 118, -23, 124, -57, 100, 5, -46, 23, -38, 76, 80, 62, -13, -116, -8, 19, 57, -100, -128, -86, -82, -96, 43, -105, 25, 44, -42, -74, -108, 78, -45, -60, 72, -20, 82, -97, -5, -26, 11, -78, -88, 1, 54, -94, -56, -3, 89, 28, 108, -65, 54, 110, 126, -2, 94, -119, -14, 42, -118, 35, 126, 43, -11, 127, -76, 36, 94, -121, -83, 122, -36, -107, -55, 65, 108, 93, 77, 10, -57, -123, 123, 58, 62, 120, -42, 76, -67, 80, 86, -48, -61, 49, -117, 94, -110, -61, -35, -111, 83, -109, -59, 20, 9, -62, -35, -97, 83, 67, 23, 117, 10, 24, 17, -25, 125, 32, -27, -27, 123, -81, -22, -67, -62, -111, 71, -68, 77, 109, 17, -7, 114, 11, -44, 7, -40, -13, 52, 46, 36, -13, 22, 5, -40, 4, 62, -102, -83, 66, -28, 8, 5, -39, -69, 99, 8, -76, 83, 12, -23, 119, 88, 107, 18, -101, 23, -94, -118, 124, -60, 14, -39, 96, 103, 49, 30, -73, -71, -18, 29, 3, -115, -6, 77, -105, 70, 44, 69, -62, -70, 123, -98, -17, -10, 33, 107, 25, 88, 41, 96, 6, -115, 114, 33, 40, 100, 15, 83, -124, 96, 7, 52, -58, 103, -38, -16, -29, 40, -9, -10, 30, -126, 11, -88, 105, 15, 18, 21, -48, 16, 56, -91, 17, -123, -11, -18, 31, 117, 25, -92, -36, 4, -35, 22, 21, 123, 56, 43, 88, -106, -2, 0, 88, 29, 64, 121, 57, 72, 31, -48, -3, 93, 84, 11, -115, 89, -47, -83, -4, 115, -102, 100, 39, 90, -50, 118, 30, -103, 13, 109, 15, -9, 41, -7, 38, 108, -118, -106, 39, 15, -39, -26, 47, 65, -93, 64, -43, -97, 49, -5, 66, -99, 109, -31, -86, 95, -75, -101, 64, 52, 57, 3, -33, 42, -124, -106, -29, -21, -65, -27, 111, 91, -18, 46, 99, -27, 58, -34, 96, -10, -34, -125, 126, -120, 71, -31, -14, 125, 93, -99, -53, -3, -108, 116, -89, -54, 121, -6, 101, 96, -87, -53, -82, 74, 5, 3, 38, 110, -23, 118, -29, -86, -41, 9, -71, 99, -81, 57, -8, 107, -65, 89, -22, 109, 25, -40, -26, 33, 125, -35, 118, 114, 4, 63, 6, 51, 41, 62, -112, -52, 97, -121, 14, 38, 104, -19, -109, -89, -100, 45, -107, -122, 101, 2, 30, 103, 29, 110, 1, -39, -109, 80, 41, -118, 74, -95, 10, 101, 52, 25, -45, -106, 110, 34, -34, 26, -62, -52, -90, 87, 56, 109, -9, -41, 37, -123, -99, 42, -125, -6, -32, -75, -48, 79, 54, 53, 58, 44, 38, 5, -98, 75, -46, -125, 1, -94, 3, -83, -67, 55, -124, -66, 36, -102, 46, 18, -90, 49, -24, 59, -64, 30, 75, -34, 16, 62, -89, 46, 41, -21, 125, 60, 15, -92, -13, -105, 8, -6, 21, 98, -67, -100, -38, -111, -7, 28, 91, 49, 52, 6, -34, 81, 46, -29, -110, 8, -82, 17, -116, 14, -97, 69, -80, -72, -84, -94, -104, 121, -6, -119, -56, -50, -27, -40, -35, -121, -122, 38, -21, -122, 121, 3, 32, -6, -89, 23, 66, 24, -93, -61, 98, -66, 51, 40, -93, -74, -101, 80, -107, -39, -22, -51, 23, 110, -13, -116, -83, -28, -118, 30, 41, -28, 61, -78, -19, 11, 72, 71, -122, 1, 86, -123, 62, 13, -25, 60, 27, -78, -4, 11, -123, -1, -47, -97, -6, 63, -52, -41, -97, 93, -97, -117, -96, -80, -35, -81, -49, -47, 127, -24, -112, -15, -44, 8, 8, -112, 52, -2, -77, -7, -9, -49, 124, -102, -3, 31, 61, -21, 109, -26};

        return ProfilePicEntity.builder()
                .name("Default Profile pic")
                .type("png")
                .image(defalutPic).build();






    }



}
