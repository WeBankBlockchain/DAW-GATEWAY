package com.webank.wsdaw.gateway.constant;

public class ApiConstant {

    public static String DAW_TXPROXY_CALL = "/bcos/call";

    public static String DAW_TXPROXY_SENDTRANSACTION = "/bcos/sendTransaction";

    public static String DAW_TXPROXY_GETBLOCKNUMBER = "/bcos/getBlockNumber";

    public static String DAW_TXPROXY_BALANCEOF = "/bcos/balanceOf";

    public static String DAW_CONFIG_LOGIN = "/access/login";

    public static String DAW_CONFIG_GETUSERNAME = "/user/getUsername";

    public static String DAW_CONFIG_GETASSETINFO = "/config/getAssetInfo";

    public static String DAW_CONFIG_GETAPPVERSION = "/config/getAppVersion";

    public static String DAW_CONFIG_GETUPDATEINFO = "/config/getLatestAppUpdateInfo";

    public static String DAW_CONFIG_GETEXPLOREINFO = "/explore/getExploreInfo";

    public static String DAW_CONFIG_GETBANNERINFO = "/explore/getBannerInfo";

    public static String DAW_CONFIG_SEARCHEXPLOREINFO = "/explore/searchExploreInfo";

    public static String DAW_CONFIG_GETWWPRIVATEKEY = "/config/getWeWorldPrivateKey";

    public static String DAW_CONFIG_HK_SENDCODE = "/hk/sendCode";

    public static String DAW_CONFIG_HK_VERIFYCODE = "/hk/verifyCode";

    public static String DAW_CONFIG_HK_REGISTER = "/hk/register";

    public static String DAW_CONFIG_HK_REGISTERSTATUS = "/hk/registerStatus";

    public static String DAW_CONFIG_HK_LOGIN = "/hk/login";

    public static String DAW_CONFIG_ASSET_SERIES_BY_TYPE = "/assetSeries/getByType";

    public static String DAW_KEYSTORE_GENERATEKEYS = "/keystore/generateUserKeys";

    public static String DAW_KEYSTORE_GETUSERKEYS = "/keystore/getUserKeys";

    public static String DAW_SAFEBOX_GETMNBYUSERID = "/account/getMnemonicByUserId";

    public static String DAW_SAFEBOX_SAVEMNINFO = "/account/saveMnemonicInfo";

    public static String DAW_SAFEBOX_CLEARMNBYUSERID = "/account/clearMnemonicByUserId";
    public static String GET_WE_WORLD_PK = "/userInfoRaw";

    public static String GET_DUIBA_BUY_URL = "/auth/duiba/buy";

    public static String GET_DUIBA_BUYLIST_URL = "/auth/duiba/buyList";

    public static String GET_DUIBA_WITHDRAW_URL = "/auth/withdrawList";

    public static String GET_ACCOUNT_BY_USER_ID = "/getAccountByUserId";

    public static String CLAIM = "/claim";

    public static String CIDS_REGISTER = "/cids/user/v1/signup";

    public static String CIDS_GET_CHANNEL_CPT = "/cids/user/v1/channel-cpt/get";

    public static String CIDS_UPDATE = "/cids/user/v1/kyc/update";

    public static String CIDS_GET_CONTRACT_ADDRESS = "/cids/user/v1/did-contract-addr/get";

    public static String CIDS_VALIDATE_ADDRESS =
            "/cids/user/v1/address-book/accountAddress/validate";

    public static String CIDS_VALIDATE_USERID = "/cids/user/v1/address-book/entity/validate";

    public static String CIDS_SET_CORP_ADDRESS = "/cids/user/v1/address-book/update";

    public static String CHDIS_ASSETLIST_BY_ADDRESSLIST = "/data/assetListByAddressList";

    public static String CHDIS_ASSETLIST_BY_SINGLEADDRESS = "/data/assetListBySingleAddress";

    public static String CHDIS_GET_ASSETDETAIL = "/data/assetDetail";

    public static String CHDIS_GET_DETAILADDRESSURL = "/data/h5JumpUrl";
}
