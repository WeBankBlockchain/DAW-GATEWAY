package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.mne.ClearMnemonicRequest;
import com.webank.wsdaw.gateway.vo.request.mne.GetMnemonicRequest;
import com.webank.wsdaw.gateway.vo.request.mne.SaveMnemonicInfoRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;

public interface MnemonicService {

    CommonResponse saveMnemonicInfo(SaveMnemonicInfoRequest request);

    CommonResponse getMnemonicByUserId(GetMnemonicRequest request);

    CommonResponse clearMnemonicByUserId(ClearMnemonicRequest request);
}
