package com.webank.wsdaw.gateway.util;

import cn.hutool.core.codec.Base58;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.utils.Hex;

@Slf4j
public class JwtIssEncoder {

    // private static final String MULTICODEC_ED25519_ENCODING = "base58btc";
    private static final String MULTICODEC_ED25519_BASE = "z";
    private static final String MULTICODEC_ED25519_HEADER = "K36";
    private static final String DID_PREFIX = "did";
    private static final String DID_METHOD = "key";
    private static final String DID_DELIMITER = ":";

    public static byte[] concatenateBytes(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static String encodeIss(byte[] publicKey) {
        byte[] headerBytes = Base58.decode(MULTICODEC_ED25519_HEADER);

        // 合并header和publicKey
        byte[] multicodecBytes = concatenateBytes(headerBytes, publicKey);

        // 对合并后的字节数组进行Base58编码，并在前面添加"z"字符
        String base58Encoded = MULTICODEC_ED25519_BASE + Base58.encode(multicodecBytes);

        // 构建最终的DID字符串
        String iss = DID_PREFIX + DID_DELIMITER + DID_METHOD + DID_DELIMITER + base58Encoded;
        return iss;
    }

    public static void main(String[] args)
            throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException,
                    IOException {
        //        byte[] secretKeyBytes = {
        //                (byte) 153, (byte) 28, (byte) 142, (byte) 224, (byte) 32, (byte) 173,
        // (byte) 206, (byte) 188, (byte) 181, (byte) 171, (byte) 7,
        //                (byte) 47, (byte) 75, (byte) 132, (byte) 38, (byte) 3, (byte) 56, (byte)
        // 145, (byte) 150, (byte) 147, (byte) 204, (byte) 6,
        //                (byte) 153, (byte) 219, (byte) 142, (byte) 15, (byte) 84, (byte) 102,
        // (byte) 113, (byte) 220, (byte) 217, (byte) 206, (byte) 5,
        //                (byte) 71, (byte) 164, (byte) 251, (byte) 37, (byte) 251, (byte) 82,
        // (byte) 21, (byte) 82, (byte) 33, (byte) 11, (byte) 145,
        //                (byte) 194, (byte) 244, (byte) 161, (byte) 55, (byte) 148, (byte) 151,
        // (byte) 105, (byte) 23, (byte) 55, (byte) 3, (byte) 56,
        //                (byte) 249, (byte) 1, (byte) 117, (byte) 129, (byte) 86, (byte) 234,
        // (byte) 156, (byte) 154, (byte) 23
        //        };
        //        byte[] publicKeyBytes = new byte[]{
        //                5, 71, -92, -5, 37, -5, 82, 21,
        //                82, 33, 11, -111, -62, -12, -95, 55,
        //                -108, -105, 105, 23, 55, 3, 56, -7,
        //                1, 117, -127, 86, -22, -100, -102, 23
        //        };
        //
        //        String hexString = HexUtil.encodeHexStr(secretKeyBytes);
        //        System.out.println(hexString);
        //        String pubHexString = HexUtil.encodeHexStr(publicKeyBytes);
        //        System.out.println(pubHexString);
        //        // 计算公钥
        //        byte[] publicKeyBytesComputed = new byte[Ed25519.PUBLIC_KEY_SIZE];
        //        Ed25519.generatePublicKey(secretKeyBytes, 0, publicKeyBytesComputed, 0);
        //        String iss = encodeIss(publicKeyBytesComputed);
        //
        //        // 转换为十六进制字符串
        //        String publicKeyHex = Hex.toHexString(publicKeyBytesComputed);

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed25519", "BC");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        // 序列化密钥为 HEX 字符串
        String privateKeyHex = Hex.toHexString(privateKey.getEncoded());
        System.out.println("random priv:" + privateKeyHex);
        String pubHex = Hex.toHexString(keyPair.getPublic().getEncoded());
        System.out.println("random pub:" + pubHex);
        System.out.println("random:" + privateKey.toString());
    }
}
