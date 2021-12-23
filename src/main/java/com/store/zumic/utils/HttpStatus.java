//package com.store.zumic.utils;
//
//import org.springframework.lang.Nullable;
//
//import static org.springframework.http.HttpStatus.VALUES;
//
//
//public enum HttpStatus {
//    CONTINUE(100, HttpStatus.Series.INFORMATIONAL, "Continue"),
//    SWITCHING_PROTOCOLS(101, HttpStatus.Series.INFORMATIONAL, "Switching Protocols"),
//    PROCESSING(102, HttpStatus.Series.INFORMATIONAL, "Processing"),
//    NETWORK_AUTHENTICATION_REQUIRED(511, HttpStatus.Series.SERVER_ERROR, "Network Authentication Required");
//
//    //private static final org.springframework.http.HttpStatus.Series GOOD = ;
//    //private static final Series GOOD = values() ;
//    private String message;
//   // private static final HttpStatus[] VALUES = values();
//    private final int value;
//    //private  static final HttpStatus statusCode;
//   private final Series series;
//   // private final String reasonPhrase;
//
//    private HttpStatus(int value, HttpStatus.Series series, String message) {
//        this.value = value;
//        this.series = series;
//        this.message = message;
//    }
//
//    public int value() {
//        return this.value;
//    }
////
//    public HttpStatus.Series series() {
//        return this.series;
//    }
//
//    public String getMessage() {
//        return this.message;
//    }
////
////    public boolean is1xxInformational() {
////        return this.series() == HttpStatus.Series.INFORMATIONAL;
////    }
////
////    public boolean is2xxSuccessful() {
////        return this.series() == org.springframework.http.HttpStatus.Series.SUCCESSFUL;
////    }
////
////    public boolean is3xxRedirection() {
////        return this.series() == org.springframework.http.HttpStatus.Series.REDIRECTION;
////    }
////
////    public boolean is4xxClientError() {
////        return this.series() == org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
////    }
////
////    public boolean is5xxServerError() {
////        return this.series() == org.springframework.http.HttpStatus.Series.SERVER_ERROR;
////    }
////
////    public boolean isError() {
////        return this.is4xxClientError() || this.is5xxServerError();
////    }
////
////    public String toString() {
////        return this.value + " " + this.name();
////    }
//
//    public static HttpStatus valueOf(int statusCode) {
//        HttpStatus status = resolve(statusCode);
//        if (status == null) {
//            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
//        } else {
//            return status;
//        }
//    }
//
////    @Nullable
////    public static HttpStatus resolve(int statusCode) {
////        org.springframework.http.HttpStatus[] var1 = VALUES;
////        int var2 = var1.length;
////
////        for(int var3 = 0; var3 < var2; ++var3) {
////            org.springframework.http.HttpStatus status = var1[var3];
////            if (status.value == statusCode) {
////                return status;
////            }
////        }
////
////        return null;
////    }
//
//    public static enum Series {
//        INFORMATIONAL(1),
//        SUCCESSFUL(2),
//        REDIRECTION(3),
//        CLIENT_ERROR(4),
//        SERVER_ERROR(5);
//
//        private final int value;
//
//        private Series(int value) {
//            this.value = value;
//        }
//
//        public int value() {
//            return this.value;
//        }
//
////        /** @deprecated */
////        @Deprecated
////        public static org.springframework.http.HttpStatus.Series valueOf(org.springframework.http.HttpStatus status) {
////            return status.series;
////        }
//
//        public static Series valueOf(int statusCode) {
//            Series series = resolve(statusCode);
//            if (series == null) {
//                throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
//            } else {
//                return series;
//            }
//        }
//
//        @Nullable
//        public static Series resolve(int statusCode) {
//            int seriesCode = statusCode / 100;
//            Series[] var2 = values();
//            int var3 = var2.length;
//
//            for(int var4 = 0; var4 < var3; ++var4) {
//                Series series = var2[var4];
//                if (series.value == seriesCode) {
//                    return series;
//                }
//            }
//
//            return null;
//        }
//    }
//}
//
