package com.talkio.smartdevice.common.constant;


public class Constants {



    public static final String HEADER_APPLICATION_JSON = "application/json";
    public static final String ERROR_CODE = "errorCode";
    public static final String ERROR_FIELD = "errorField";
    public static final String ERROR_MESSAGE_CODE = "errorMessageCode";
    public static final String ERROR_LIST_MESSAGE = "errorList";
    public static final String ERROR_MESSAGE_VALUE = "common.error.";
    public static final String SUCCESS_CODE = "successCode";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String SUCCESS_MESSAGE = "successMessage";


    public static String ENTITY_USER = "com.indiantech.conglu.authentication.model.";
    public static String ENTITY_ALERT_TYPE = "com.indiantech.conglu.alert.";
    public static String LOGIN_TABLE = "LoginUser";

    public static String ENV_TEST = "test";





    public static String SUCCESS= "success";
    public static String ASC= "asc";
    public static String DESC= "desc";
    public static String TRUE = "true";
    public static String FALSE = "false";

    public static String STRING_BLANK="";
    public static String COMMA_SPACE_SEPARATOR=", ";
    public static final String DATE = "date";
    public static String QUERY_ALERT_SUBJECT =  "Query Alert!!";
    public static String SCHEDULER_ALERT_SUBJECT = "Scheduler Alert!!";
    public static String STRING_SPACE = " ";
    public static String STRING_HYPEN = "-";


    public static String ENTITY_PACKAGE_NAME = "com.indiantech.conglu.entity.mongo.";

    public static enum ErrorType {
        Error1(1, "label.conglu.error.code.001"),
        Error2(2, "label.conglu.error.code.002"),
        Error3(3, "label.conglu.error.code.003"),
        Error4(4, "label.conglu.error.code.004"),
        Error5(5, "label.conglu.error.code.005"),
        Error6(6, "label.conglu.error.code.006"),
        Error7(7, "label.conglu.error.code.007"),
        Error8(8, "label.conglu.error.code.008"),
        Error9(9, "label.conglu.error.code.009"),
        Error10(10, "label.conglu.error.code.010"),
        Error11(11, "label.conglu.error.code.011"),
        Error12(12, "label.conglu.error.code.012"),
        Error13(13, "label.conglu.error.code.013"),
        Error14(14, "label.conglu.error.code.014"),
        Error15(15, "label.conglu.error.code.015"),
        Error16(16, "label.conglu.error.code.016"),
        Error17(17, "label.conglu.error.code.017"),
        Error18(18, "label.conglu.error.code.018"),
        Error19(19, "label.conglu.error.code.019"),
        Error20(20, "label.conglu.error.code.020"),
        Error21(21, "label.conglu.error.code.021"),
        Error22(22, "label.conglu.error.code.022"),
        Error23(23, "label.conglu.error.code.023"),
        Error24(24, "label.conglu.error.code.024"),
        Error25(25, "label.conglu.error.code.025"),
        Error26(26, "label.conglu.error.code.026"),
        Error27(27, "label.conglu.error.code.027"),
        Error28(28, "label.conglu.error.code.028"),
        Error29(29, "label.conglu.error.code.029"),
        Error30(30, "label.conglu.error.code.030"),
        Error31(31, "label.conglu.error.code.031"),
        Error32(32, "label.conglu.error.code.032"),
        Error33(33, "label.conglu.error.code.033"),
        Error34(34, "label.conglu.error.code.034"),
        Error35(35, "label.conglu.error.code.035");

        private int errorId;
        private String value;
        ErrorType( int errorId,String value) {
            this.errorId = errorId;
            this.value = value;
        }
        public int getErrorId() {
            return errorId;
        }
        public void setErrorId(int errorId) {
            this.errorId = errorId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static enum SuccessType {
        Success1(1, "label.conglu.success.code.001"),
        Success2(2, "label.conglu.success.code.002"),
        Success3(3, "label.conglu.success.code.003"),
        Success4(4, "label.conglu.success.code.004"),
        Success5(5, "label.conglu.success.code.005"),
        Success6(6, "label.conglu.success.code.006"),
        Success7(7, "label.conglu.success.code.007"),
        Success8(8, "label.conglu.success.code.008"),
        Success9(9, "label.conglu.success.code.009"),
        Success10(10, "label.conglu.success.code.010");

        private int successId;
        private String value;
        SuccessType( int successId,String value) {
            this.successId = successId;
            this.value = value;
        }
        public int getSuccessId() {
            return successId;
        }
        public void setSuccessId(int successId) {
            this.successId = successId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
