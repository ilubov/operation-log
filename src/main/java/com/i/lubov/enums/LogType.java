package com.i.lubov.enums;

public enum LogType {
    INFO("INFO", "普通日志"),
    ERROR("ERROR", "异常日志");

    private String code;
    private String desc;

    LogType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static LogType ofEnum(String code) {
        LogType[] var1 = values();
        int var2 = var1.length;
        for (int var3 = 0; var3 < var2; ++var3) {
            LogType type = var1[var3];
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
