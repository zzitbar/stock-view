package cn.coderme.stockview.dataobtain.baseinfo.dto;

import java.util.List;

/**
 * Created By Administrator
 * Date:2018/6/15
 * Time:15:37
 */
public class StockInfoCrawlDto {

    /**
     * actionErrors : []
     * actionMessages : []
     * errorMessages : []
     * errors : {}
     * fieldErrors : {}
     * isPagination : false
     * jsonCallBack : jsonpCallback97182
     * locale : zh_CN
     * pageHelp : {"beginPage":1,"cacheSize":5,"data":null,"endDate":null,"endPage":null,"objectResult":null,"pageCount":null,"pageNo":1,"pageSize":10,"searchDate":null,"sort":null,"startDate":null,"total":0}
     * result : [{"ENGLISH_ABBR":"BESTSUN RNREGY ","LEGAL_REPRESENTATIVE":"王东海                        ","REPR_PHONE":"-","CSRC_CODE_DESC":"电力、热力、燃气及水生产和供应业","E_MAIL_ADDRESS":"baichuandsh@163.com","SSE_CODE_DESC":"工业","WWW_ADDRESS":"HTTP://www.bestsungas.com","SECURITY_CODE_A":"600681","SECURITY_CODE_B":"-","COMPANY_CODE":"600681","OFFICE_ADDRESS":"北京市朝阳区建国门外大街甲6号中环世贸中心C座601","SECURITY_ABBR_A":"百川能源","COMPANY_ABBR":"百川能源","AREA_NAME_DESC":"湖北","STATE_CODE_A_DESC":"上市","CSRC_MIDDLE_CODE_DESC":"-","FULLNAME":"百川能源股份有限公司","FOREIGN_LISTING_ADDRESS":"-","COMPANY_ADDRESS":"武汉市汉阳区阳新路特一号","STATE_CODE_B_DESC":"-","CSRC_GREAT_CODE_DESC":"燃气生产和供应业","SECURITY_30_DESC":"否","CHANGEABLE_BOND_CODE":"-","FOREIGN_LISTING_DESC":"否","CHANGEABLE_BOND_ABBR":"-","SECURITY_CODE_A_SZ":"-","FULL_NAME_IN_ENGLISH":"BESTSUN RNREGY  CO., LTD.","OFFICE_ZIP":"100022"}]
     * sqlId : COMMON_SSE_ZQPZ_GP_GPLB_C
     * texts : null
     * type :
     * validateCode :
     */

    private ErrorsBean errors;
    private FieldErrorsBean fieldErrors;
    private String isPagination;
    private String jsonCallBack;
    private String locale;
    private PageHelpBean pageHelp;
    private String sqlId;
    private Object texts;
    private String type;
    private String validateCode;
    private List<?> actionErrors;
    private List<?> actionMessages;
    private List<?> errorMessages;
    private List<ResultBean> result;

    public ErrorsBean getErrors() {
        return errors;
    }

    public void setErrors(ErrorsBean errors) {
        this.errors = errors;
    }

    public FieldErrorsBean getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(FieldErrorsBean fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getIsPagination() {
        return isPagination;
    }

    public void setIsPagination(String isPagination) {
        this.isPagination = isPagination;
    }

    public String getJsonCallBack() {
        return jsonCallBack;
    }

    public void setJsonCallBack(String jsonCallBack) {
        this.jsonCallBack = jsonCallBack;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public PageHelpBean getPageHelp() {
        return pageHelp;
    }

    public void setPageHelp(PageHelpBean pageHelp) {
        this.pageHelp = pageHelp;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public Object getTexts() {
        return texts;
    }

    public void setTexts(Object texts) {
        this.texts = texts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public List<?> getActionErrors() {
        return actionErrors;
    }

    public void setActionErrors(List<?> actionErrors) {
        this.actionErrors = actionErrors;
    }

    public List<?> getActionMessages() {
        return actionMessages;
    }

    public void setActionMessages(List<?> actionMessages) {
        this.actionMessages = actionMessages;
    }

    public List<?> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<?> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ErrorsBean {
    }

    public static class FieldErrorsBean {
    }

    public static class PageHelpBean {
        /**
         * beginPage : 1
         * cacheSize : 5
         * data : null
         * endDate : null
         * endPage : null
         * objectResult : null
         * pageCount : null
         * pageNo : 1
         * pageSize : 10
         * searchDate : null
         * sort : null
         * startDate : null
         * total : 0
         */

        private int beginPage;
        private int cacheSize;
        private Object data;
        private Object endDate;
        private Object endPage;
        private Object objectResult;
        private Object pageCount;
        private int pageNo;
        private int pageSize;
        private Object searchDate;
        private Object sort;
        private Object startDate;
        private int total;

        public int getBeginPage() {
            return beginPage;
        }

        public void setBeginPage(int beginPage) {
            this.beginPage = beginPage;
        }

        public int getCacheSize() {
            return cacheSize;
        }

        public void setCacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getEndDate() {
            return endDate;
        }

        public void setEndDate(Object endDate) {
            this.endDate = endDate;
        }

        public Object getEndPage() {
            return endPage;
        }

        public void setEndPage(Object endPage) {
            this.endPage = endPage;
        }

        public Object getObjectResult() {
            return objectResult;
        }

        public void setObjectResult(Object objectResult) {
            this.objectResult = objectResult;
        }

        public Object getPageCount() {
            return pageCount;
        }

        public void setPageCount(Object pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public Object getSearchDate() {
            return searchDate;
        }

        public void setSearchDate(Object searchDate) {
            this.searchDate = searchDate;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getStartDate() {
            return startDate;
        }

        public void setStartDate(Object startDate) {
            this.startDate = startDate;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class ResultBean {
        /**
         * ENGLISH_ABBR : BESTSUN RNREGY
         * LEGAL_REPRESENTATIVE : 王东海
         * REPR_PHONE : -
         * CSRC_CODE_DESC : 电力、热力、燃气及水生产和供应业
         * E_MAIL_ADDRESS : baichuandsh@163.com
         * SSE_CODE_DESC : 工业
         * WWW_ADDRESS : HTTP://www.bestsungas.com
         * SECURITY_CODE_A : 600681
         * SECURITY_CODE_B : -
         * COMPANY_CODE : 600681
         * OFFICE_ADDRESS : 北京市朝阳区建国门外大街甲6号中环世贸中心C座601
         * SECURITY_ABBR_A : 百川能源
         * COMPANY_ABBR : 百川能源
         * AREA_NAME_DESC : 湖北
         * STATE_CODE_A_DESC : 上市
         * CSRC_MIDDLE_CODE_DESC : -
         * FULLNAME : 百川能源股份有限公司
         * FOREIGN_LISTING_ADDRESS : -
         * COMPANY_ADDRESS : 武汉市汉阳区阳新路特一号
         * STATE_CODE_B_DESC : -
         * CSRC_GREAT_CODE_DESC : 燃气生产和供应业
         * SECURITY_30_DESC : 否
         * CHANGEABLE_BOND_CODE : -
         * FOREIGN_LISTING_DESC : 否
         * CHANGEABLE_BOND_ABBR : -
         * SECURITY_CODE_A_SZ : -
         * FULL_NAME_IN_ENGLISH : BESTSUN RNREGY  CO., LTD.
         * OFFICE_ZIP : 100022
         */

        private String ENGLISH_ABBR;
        private String LEGAL_REPRESENTATIVE;
        private String REPR_PHONE;
        private String CSRC_CODE_DESC;
        private String E_MAIL_ADDRESS;
        private String SSE_CODE_DESC;
        private String WWW_ADDRESS;
        private String SECURITY_CODE_A;
        private String SECURITY_CODE_B;
        private String COMPANY_CODE;
        private String OFFICE_ADDRESS;
        private String SECURITY_ABBR_A;
        private String COMPANY_ABBR;
        private String AREA_NAME_DESC;
        private String STATE_CODE_A_DESC;
        private String CSRC_MIDDLE_CODE_DESC;
        private String FULLNAME;
        private String FOREIGN_LISTING_ADDRESS;
        private String COMPANY_ADDRESS;
        private String STATE_CODE_B_DESC;
        private String CSRC_GREAT_CODE_DESC;
        private String SECURITY_30_DESC;
        private String CHANGEABLE_BOND_CODE;
        private String FOREIGN_LISTING_DESC;
        private String CHANGEABLE_BOND_ABBR;
        private String SECURITY_CODE_A_SZ;
        private String FULL_NAME_IN_ENGLISH;
        private String OFFICE_ZIP;

        public String getENGLISH_ABBR() {
            return ENGLISH_ABBR;
        }

        public void setENGLISH_ABBR(String ENGLISH_ABBR) {
            this.ENGLISH_ABBR = ENGLISH_ABBR;
        }

        public String getLEGAL_REPRESENTATIVE() {
            return LEGAL_REPRESENTATIVE;
        }

        public void setLEGAL_REPRESENTATIVE(String LEGAL_REPRESENTATIVE) {
            this.LEGAL_REPRESENTATIVE = LEGAL_REPRESENTATIVE;
        }

        public String getREPR_PHONE() {
            return REPR_PHONE;
        }

        public void setREPR_PHONE(String REPR_PHONE) {
            this.REPR_PHONE = REPR_PHONE;
        }

        public String getCSRC_CODE_DESC() {
            return CSRC_CODE_DESC;
        }

        public void setCSRC_CODE_DESC(String CSRC_CODE_DESC) {
            this.CSRC_CODE_DESC = CSRC_CODE_DESC;
        }

        public String getE_MAIL_ADDRESS() {
            return E_MAIL_ADDRESS;
        }

        public void setE_MAIL_ADDRESS(String E_MAIL_ADDRESS) {
            this.E_MAIL_ADDRESS = E_MAIL_ADDRESS;
        }

        public String getSSE_CODE_DESC() {
            return SSE_CODE_DESC;
        }

        public void setSSE_CODE_DESC(String SSE_CODE_DESC) {
            this.SSE_CODE_DESC = SSE_CODE_DESC;
        }

        public String getWWW_ADDRESS() {
            return WWW_ADDRESS;
        }

        public void setWWW_ADDRESS(String WWW_ADDRESS) {
            this.WWW_ADDRESS = WWW_ADDRESS;
        }

        public String getSECURITY_CODE_A() {
            return SECURITY_CODE_A;
        }

        public void setSECURITY_CODE_A(String SECURITY_CODE_A) {
            this.SECURITY_CODE_A = SECURITY_CODE_A;
        }

        public String getSECURITY_CODE_B() {
            return SECURITY_CODE_B;
        }

        public void setSECURITY_CODE_B(String SECURITY_CODE_B) {
            this.SECURITY_CODE_B = SECURITY_CODE_B;
        }

        public String getCOMPANY_CODE() {
            return COMPANY_CODE;
        }

        public void setCOMPANY_CODE(String COMPANY_CODE) {
            this.COMPANY_CODE = COMPANY_CODE;
        }

        public String getOFFICE_ADDRESS() {
            return OFFICE_ADDRESS;
        }

        public void setOFFICE_ADDRESS(String OFFICE_ADDRESS) {
            this.OFFICE_ADDRESS = OFFICE_ADDRESS;
        }

        public String getSECURITY_ABBR_A() {
            return SECURITY_ABBR_A;
        }

        public void setSECURITY_ABBR_A(String SECURITY_ABBR_A) {
            this.SECURITY_ABBR_A = SECURITY_ABBR_A;
        }

        public String getCOMPANY_ABBR() {
            return COMPANY_ABBR;
        }

        public void setCOMPANY_ABBR(String COMPANY_ABBR) {
            this.COMPANY_ABBR = COMPANY_ABBR;
        }

        public String getAREA_NAME_DESC() {
            return AREA_NAME_DESC;
        }

        public void setAREA_NAME_DESC(String AREA_NAME_DESC) {
            this.AREA_NAME_DESC = AREA_NAME_DESC;
        }

        public String getSTATE_CODE_A_DESC() {
            return STATE_CODE_A_DESC;
        }

        public void setSTATE_CODE_A_DESC(String STATE_CODE_A_DESC) {
            this.STATE_CODE_A_DESC = STATE_CODE_A_DESC;
        }

        public String getCSRC_MIDDLE_CODE_DESC() {
            return CSRC_MIDDLE_CODE_DESC;
        }

        public void setCSRC_MIDDLE_CODE_DESC(String CSRC_MIDDLE_CODE_DESC) {
            this.CSRC_MIDDLE_CODE_DESC = CSRC_MIDDLE_CODE_DESC;
        }

        public String getFULLNAME() {
            return FULLNAME;
        }

        public void setFULLNAME(String FULLNAME) {
            this.FULLNAME = FULLNAME;
        }

        public String getFOREIGN_LISTING_ADDRESS() {
            return FOREIGN_LISTING_ADDRESS;
        }

        public void setFOREIGN_LISTING_ADDRESS(String FOREIGN_LISTING_ADDRESS) {
            this.FOREIGN_LISTING_ADDRESS = FOREIGN_LISTING_ADDRESS;
        }

        public String getCOMPANY_ADDRESS() {
            return COMPANY_ADDRESS;
        }

        public void setCOMPANY_ADDRESS(String COMPANY_ADDRESS) {
            this.COMPANY_ADDRESS = COMPANY_ADDRESS;
        }

        public String getSTATE_CODE_B_DESC() {
            return STATE_CODE_B_DESC;
        }

        public void setSTATE_CODE_B_DESC(String STATE_CODE_B_DESC) {
            this.STATE_CODE_B_DESC = STATE_CODE_B_DESC;
        }

        public String getCSRC_GREAT_CODE_DESC() {
            return CSRC_GREAT_CODE_DESC;
        }

        public void setCSRC_GREAT_CODE_DESC(String CSRC_GREAT_CODE_DESC) {
            this.CSRC_GREAT_CODE_DESC = CSRC_GREAT_CODE_DESC;
        }

        public String getSECURITY_30_DESC() {
            return SECURITY_30_DESC;
        }

        public void setSECURITY_30_DESC(String SECURITY_30_DESC) {
            this.SECURITY_30_DESC = SECURITY_30_DESC;
        }

        public String getCHANGEABLE_BOND_CODE() {
            return CHANGEABLE_BOND_CODE;
        }

        public void setCHANGEABLE_BOND_CODE(String CHANGEABLE_BOND_CODE) {
            this.CHANGEABLE_BOND_CODE = CHANGEABLE_BOND_CODE;
        }

        public String getFOREIGN_LISTING_DESC() {
            return FOREIGN_LISTING_DESC;
        }

        public void setFOREIGN_LISTING_DESC(String FOREIGN_LISTING_DESC) {
            this.FOREIGN_LISTING_DESC = FOREIGN_LISTING_DESC;
        }

        public String getCHANGEABLE_BOND_ABBR() {
            return CHANGEABLE_BOND_ABBR;
        }

        public void setCHANGEABLE_BOND_ABBR(String CHANGEABLE_BOND_ABBR) {
            this.CHANGEABLE_BOND_ABBR = CHANGEABLE_BOND_ABBR;
        }

        public String getSECURITY_CODE_A_SZ() {
            return SECURITY_CODE_A_SZ;
        }

        public void setSECURITY_CODE_A_SZ(String SECURITY_CODE_A_SZ) {
            this.SECURITY_CODE_A_SZ = SECURITY_CODE_A_SZ;
        }

        public String getFULL_NAME_IN_ENGLISH() {
            return FULL_NAME_IN_ENGLISH;
        }

        public void setFULL_NAME_IN_ENGLISH(String FULL_NAME_IN_ENGLISH) {
            this.FULL_NAME_IN_ENGLISH = FULL_NAME_IN_ENGLISH;
        }

        public String getOFFICE_ZIP() {
            return OFFICE_ZIP;
        }

        public void setOFFICE_ZIP(String OFFICE_ZIP) {
            this.OFFICE_ZIP = OFFICE_ZIP;
        }
    }
}