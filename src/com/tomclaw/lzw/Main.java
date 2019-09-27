package com.tomclaw.lzw;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

public class Main {

    public static void main(String[] args) {
        try {
            ByteArrayInputStream input;
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            Lzw lzw_compression = new Lzw();
            // String OriginalString = "{\"files\":[{\"size\":\"2332167\",\"sha1\":\"4369eafb2c810b24b698f87a4b09f7d11d8e8f81\",\"time\":\"1552952751\",\"def_label\":\"\",\"labels\":{\"ru\":\"PowerAudio Pro\"},\"package\":\"xsoftstudio.musicplayer.pro\",\"ver_name\":\"7.1.2\",\"ver_code\":\"207\",\"sdk_version\":\"16\",\"permissions\":[\"android.permission.WAKE_LOCK\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.WRITE_SETTINGS\",\"android.permission.INTERNET\",\"android.permission.ACCESS_NETWORK_STATE\"],\"downloads\":\"11\",\"download_time\":\"1564816677\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"436r13589\",\"android\":\"4.1\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=R0JISTRzUGg3UG12RXZoREZORk0rT2xCMXozb1BvRVVXaGhZS0tFK1crM21aYTFKYUxQSUhvOWJ1Y3ZWVFZ2cA==\"},{\"size\":\"2364440\",\"sha1\":\"ab5cbc0c74284b59785a15b372bfd2e01594afdf\",\"time\":\"1552929518\",\"def_label\":\"\",\"labels\":{\"ru\":\"PowerAudio Pro\"},\"package\":\"xsoftstudio.musicplayer.pro\",\"ver_name\":\"7.0.5\",\"ver_code\":\"201\",\"sdk_version\":\"16\",\"permissions\":[\"android.permission.WAKE_LOCK\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.WRITE_SETTINGS\",\"android.permission.INTERNET\",\"android.permission.ACCESS_NETWORK_STATE\"],\"downloads\":\"6\",\"download_time\":\"1552964962\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"ab5r13581\",\"android\":\"4.1\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=RGZxQVpoR0Q4R1RBRGxFRmdJT3lldkdjeTYvWVRDWGtJZ0ZmanR4c1FtM3FYalBIa2VvcjRkTFhhV1NvY1N6RQ==\"},{\"size\":\"11544987\",\"sha1\":\"7bbfe9a6f64d3a46123bc2b6be67341d175eae24\",\"time\":\"1552916740\",\"def_label\":\"rushplayer\",\"labels\":{\"ru\":\"rushplayer\"},\"package\":\"lzr.fafa.bfq\",\"ver_name\":\"1.6.8\",\"ver_code\":\"1020504\",\"sdk_version\":\"7\",\"permissions\":[\"android.permission.MODIFY_AUDIO_SETTINGS\",\"android.permission.INTERNET\",\"android.permission.READ_PHONE_STATE\",\"android.permission.READ_LOGS\",\"android.permission.VIBRATE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.WRITE_SETTINGS\",\"android.permission.WAKE_LOCK\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"15\",\"download_time\":\"1558536317\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"7bbr13571\",\"android\":\"2.1\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=VndFajVKUVJNbG9uRlhpNFRUUHl1N3NOSnZkL1BldmJHQ3dpclBrZFRkMW9pNmZuOXE4ZHRyMHJ3UGJ0cEJpUw==\"},{\"size\":\"1518346\",\"sha1\":\"113aee13e25ed692e8e2745145a1f04293b8bc5d\",\"time\":\"1552878299\",\"def_label\":\"Masked GPS\",\"labels\":{\"ru\":\"Masked GPS\"},\"package\":\"com.nigga.fakegpsdonate\",\"ver_name\":\"2.0.7\",\"ver_code\":\"2070\",\"sdk_version\":\"15\",\"permissions\":[\"android.permission.ACCESS_MOCK_LOCATION\",\"android.permission.WRITE_SETTINGS\",\"android.permission.WRITE_SECURE_SETTINGS\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.INTERNET\",\"android.permission.RECEIVE_BOOT_COMPLETED\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"6\",\"download_time\":\"1557054179\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"113r13550\",\"android\":\"4.0.3\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=cnpTZ1Q2Q1l4VERidTl6d25uaGVUOFNTaTdWdEFTU3lhdVRURE5FbjAwSmtaZm02MzU0TjhBUXVxUkVTVnd3bA==\"},{\"size\":\"8468526\",\"sha1\":\"0c1af85869b39d95861d2ed138e797ce3e1e76d1\",\"time\":\"1552856340\",\"def_label\":\"VPN One\",\"labels\":{\"ru\":\"VPN One\"},\"package\":\"com.vpnone\",\"ver_name\":\"1.13\",\"ver_code\":\"31\",\"sdk_version\":\"16\",\"permissions\":[\"android.permission.INTERNET\",\"android.permission.SYSTEM_ALERT_WINDOW\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.USE_FINGERPRINT\",\"com.google.android.c2dm.permission.RECEIVE\",\"android.permission.WAKE_LOCK\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_PHONE_STATE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"31\",\"download_time\":\"1566676370\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"0c1r13546\",\"android\":\"4.1\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=Sll6VFU3ZURKME5NYlZGSzI4dVQ3akhwZFNsYVhkbEJWeDBxanhxUDVXMmFIeGdvejlubWJmQzladk96cnFkZA==\"},{\"size\":\"2885614\",\"sha1\":\"3b1dc904c6a2b901dc1103df624ff3680c9234da\",\"time\":\"1552847979\",\"def_label\":\"BusyBox Pro\",\"labels\":{\"ru\":\"BusyBox Pro\"},\"package\":\"stericson.busybox.donate\",\"ver_name\":\"70\",\"ver_code\":\"230\",\"sdk_version\":\"15\",\"permissions\":[\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.RECEIVE_BOOT_COMPLETED\"],\"downloads\":\"26\",\"download_time\":\"1563569394\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"3b1r13544\",\"android\":\"4.0.3\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=Wkh2dDZaaTZCd1NMWmZFTzF1anNiNiswSG92K1FvT3I2QkdIVkFDblJVZ25aQnc4S2xXL3hreDlVeFZSa2xmMw==\"},{\"size\":\"10558881\",\"sha1\":\"e27514a214d558969fa43265d25f4fcb48f5146f\",\"time\":\"1552738033\",\"def_label\":\"PLUS Optimizer\",\"labels\":{\"ru\":\"PLUS Optimizer\"},\"package\":\"com.plus.optimizer\",\"ver_name\":\"1.0.5.0102\",\"ver_code\":\"5\",\"sdk_version\":\"15\",\"permissions\":[\"android.permission.KILL_BACKGROUND_PROCESSES\",\"android.permission.INTERNET\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.RESTART_PACKAGES\",\"android.permission.GET_PACKAGE_SIZE\",\"android.permission.GET_TASKS\",\"android.permission.ACCESS_WIFI_STATE\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.FORCE_STOP_PACKAGE\",\"android.permission.CLEAR_APP_CACHE\",\"com.android.launcher.permission.INSTALL_SHORTCUT\",\"com.android.launcher.permission.UNINSTALL_SHORTCUT\",\"android.permission.BATTERY_STATS\",\"android.permission.DOWNLOAD_WITHOUT_NOTIFICATION\",\"android.permission.RECEIVE_BOOT_COMPLETED\",\"android.permission.CHANGE_CONFIGURATION\",\"com.android.launcher.permission.READ_SETTINGS\",\"com.android.launcher.permission.WRITE_SETTINGS\",\"com.android.launcher2.permission.READ_SETTINGS\",\"com.android.launcher2.permission.WRITE_SETTINGS\",\"com.android.launcher3.permission.READ_SETTINGS\",\"com.android.launcher3.permission.WRITE_SETTINGS\",\"org.adw.launcher.permission.READ_SETTINGS\",\"org.adw.launcher.permission.WRITE_SETTINGS\",\"com.htc.launcher.permission.READ_SETTINGS\",\"com.htc.launcher.permission.WRITE_SETTINGS\",\"com.qihoo360.launcher.permission.READ_SETTINGS\",\"com.qihoo360.launcher.permission.WRITE_SETTINGS\",\"com.lge.launcher.permission.READ_SETTINGS\",\"com.lge.launcher.permission.WRITE_SETTINGS\",\"net.qihoo.launcher.permission.READ_SETTINGS\",\"net.qihoo.launcher.permission.WRITE_SETTINGS\",\"org.adwfreak.launcher.permission.READ_SETTINGS\",\"org.adwfreak.launcher.permission.WRITE_SETTINGS\",\"org.adw.launcher_donut.permission.READ_SETTINGS\",\"org.adw.launcher_donut.permission.WRITE_SETTINGS\",\"com.huawei.launcher3.permission.READ_SETTINGS\",\"com.huawei.launcher3.permission.WRITE_SETTINGS\",\"com.fede.launcher.permission.READ_SETTINGS\",\"com.fede.launcher.permission.WRITE_SETTINGS\",\"com.sec.android.app.twlauncher.settings.READ_SETTINGS\",\"com.sec.android.app.twlauncher.settings.WRITE_SETTINGS\",\"com.anddoes.launcher.permission.READ_SETTINGS\",\"com.anddoes.launcher.permission.WRITE_SETTINGS\",\"com.tencent.qqlauncher.permission.READ_SETTINGS\",\"com.tencent.qqlauncher.permission.WRITE_SETTINGS\",\"com.huawei.launcher2.permission.READ_SETTINGS\",\"com.huawei.launcher2.permission.WRITE_SETTINGS\",\"com.android.mylauncher.permission.READ_SETTINGS\",\"com.android.mylauncher.permission.WRITE_SETTINGS\",\"com.ebproductions.android.launcher.permission.READ_SETTINGS\",\"com.ebproductions.android.launcher.permission.WRITE_SETTINGS\",\"com.oppo.launcher.permission.READ_SETTINGS\",\"com.oppo.launcher.permission.WRITE_SETTINGS\",\"com.lenovo.launcher.permission.READ_SETTINGS\",\"com.lenovo.launcher.permission.WRITE_SETTINGS\",\"com.huawei.android.launcher.permission.READ_SETTINGS\",\"com.huawei.android.launcher.permission.WRITE_SETTINGS\",\"telecom.mdesk.permission.READ_SETTINGS\",\"telecom.mdesk.permission.WRITE_SETTINGS\",\"dianxin.permission.ACCESS_LAUNCHER_DATA\",\"com.google.android.launcher.permission.READ_SETTINGS\",\"com.google.android.launcher.permission.WRITE_SETTINGS\",\"com.google.android.launcher.permission.CONTENT_REDIRECT\",\"com.yulong.android.launcher3.permission.WRITE_SETTINGS\",\"com.yulong.android.launcher3.permission.READ_SETTINGS\",\"com.bbk.launcher2.permission.READ_SETTINGS\",\"com.bbk.launcher2.permission.WRITE_SETTINGS\",\"com.android.browser.permission.READ_HISTORY_BOOKMARKS\",\"com.android.browser.permission.WRITE_HISTORY_BOOKMARKS\",\"android.permission.READ_PHONE_STATE\",\"android.permission.ACCESS_COARSE_LOCATION\",\"android.permission.WRITE_SETTINGS\",\"android.permission.SYSTEM_ALERT_WINDOW\",\"android.permission.ACCESS_FINE_LOCATION\",\"android.permission.SYSTEM_OVERLAY_WINDOW\",\"android.permissoon.READ_PHONE_STATE\",\"android.permission.READ_SETTINGS\",\"com.qihoo360.home.permission.READ_SETTINGS\",\"com.qihoo360.home.permission.WRITE_SETTINGS\",\"com.sonymobile.home.permission.PROVIDER_ACCESS_MODIFY_CONFIGURATION\",\"com.gionee.amisystem.launcher.permission.READ_SETTINGS\",\"com.miui.mihome2.permission.READ_SETTINGS\",\"com.miui.mihome2.permission.WRITE_SETTINGS\",\"com.company.contentprovider.READ_DATABASE\",\"com.company.contentprovider.WRITE_DATABASE\",\"android.permission.WAKE_LOCK\",\"com.google.android.c2dm.permission.RECEIVE\",\"com.plus.optimizer.permission.C2D_MESSAGE\"],\"downloads\":\"25\",\"download_time\":\"1567340541\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"e27r13495\",\"android\":\"4.0.3\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=elFaNFdyY0tzR2E3eEdGRThydCt5ZDgxbXJoZzRMS2lhdFVjMmtYa0NuTWF1OWpPMSt0Ti9nYWxscHQrZy85Nw==\"},{\"size\":\"12832809\",\"sha1\":\"5717a836f00a4cc6ea8f623e021f5439b790e602\",\"time\":\"1552676069\",\"def_label\":\"Relax Player\",\"labels\":{\"ru\":\"Relax Плеер\"},\"package\":\"com.rexsoftapp.player\",\"ver_name\":\"R-1.5.100\",\"ver_code\":\"30510\",\"sdk_version\":\"21\",\"permissions\":[\"android.permission.READ_PHONE_STATE\",\"android.permission.WAKE_LOCK\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.INTERNET\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.ACCESS_WIFI_STATE\",\"android.permission.BROADCAST_STICKY\",\"android.permission.RECEIVE_BOOT_COMPLETED\",\"android.permission.FOREGROUND_SERVICE\",\"android.permission.REQUEST_INSTALL_PACKAGES\",\"com.google.android.c2dm.permission.RECEIVE\",\"com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE\"],\"downloads\":\"12\",\"download_time\":\"1567339466\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"571r13482\",\"android\":\"5.0\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=VmRHQTFJUW5VdTcxcmFka0JSMWxMcjVNYjdzZ1ZZUm9GRXhrWTFrZkp2SWl4eDVhb0xUakIrNVYvSlhLdUZJag==\"},{\"size\":\"13060902\",\"sha1\":\"59d1fdb954e5ee30278fb0d285888e0b04d5fa3e\",\"time\":\"1552653951\",\"def_label\":\"MX Player Pro\",\"labels\":{\"ru\":\"MX Player Pro\"},\"package\":\"com.mxtech.videoplayer.pro\",\"ver_name\":\"1.10.47\",\"ver_code\":\"1310001117\",\"sdk_version\":\"14\",\"permissions\":[\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.ACCESS_WIFI_STATE\",\"android.permission.INTERNET\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.SYSTEM_ALERT_WINDOW\",\"android.permission.KILL_BACKGROUND_PROCESSES\",\"android.permission.WAKE_LOCK\",\"android.permission.BLUETOOTH\",\"android.permission.VIBRATE\",\"android.permission.DISABLE_KEYGUARD\",\"android.permission.READ_EXTERNAL_STORAGE\",\"com.google.android.c2dm.permission.RECEIVE\",\"com.mxtech.videoplayer.pro.permission.C2D_MESSAGE\"],\"downloads\":\"57\",\"download_time\":\"1558625310\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"59dr13456\",\"android\":\"4.0\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=MmEybmp4aHpXUDVVNW9IYWI4S1VIcnA2czhUUU0zN3lhYWV4UHN1TGlrQ2hXU3d6STJxV2d0cmVDL1hPb3p5Mw==\"},{\"size\":\"66846480\",\"sha1\":\"c6e22a288ae34b8f8ea676dcbd12acc978b9b572\",\"time\":\"1552641163\",\"def_label\":\"Aloha\",\"labels\":{\"fa\":\"Aloha\",\"ja\":\"Aloha\",\"de\":\"Aloha\",\"th\":\"Aloha\",\"hi\":\"Aloha\",\"vi\":\"Aloha\",\"uk\":\"Aloha\",\"el\":\"Aloha\",\"nl\":\"Aloha\",\"pl\":\"Aloha\",\"in\":\"Aloha\",\"ko\":\"Aloha\",\"ro\":\"Aloha\",\"ar\":\"Aloha\",\"fr\":\"Aloha\",\"tr\":\"Aloha\",\"cs\":\"Aloha\",\"es\":\"Aloha\",\"ms\":\"Aloha\",\"it\":\"Aloha\",\"pt\":\"Aloha\",\"hu\":\"Aloha\",\"ru\":\"Aloha\",\"sv\":\"Aloha\",\"iw\":\"Aloha\",\"zh_CN\":\"Aloha\",\"zh_TW\":\"Aloha\"},\"package\":\"com.alohamobile.browser\",\"ver_name\":\"2.1.0.0\",\"ver_code\":\"121362\",\"sdk_version\":\"21\",\"permissions\":[\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.INTERNET\",\"android.permission.ACCESS_WIFI_STATE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.ACCESS_COARSE_LOCATION\",\"android.permission.ACCESS_FINE_LOCATION\",\"android.permission.VIBRATE\",\"android.permission.WAKE_LOCK\",\"com.android.launcher.permission.INSTALL_SHORTCUT\",\"com.android.launcher.permission.UNINSTALL_SHORTCUT\",\"com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY\",\"android.permission.CAMERA\",\"android.permission.USE_FINGERPRINT\",\"com.android.vending.BILLING\",\"android.permission.FOREGROUND_SERVICE\",\"com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE\",\"android.permission.READ_PHONE_STATE\",\"com.google.android.c2dm.permission.RECEIVE\"],\"downloads\":\"30\",\"download_time\":\"1557361353\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"c6er13448\",\"android\":\"5.0\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=Q25laWhSWWQva1VjbEFscWljNFRKTGUyQnVCeTAxdGlibGZvdkxLZ2NHVHFnaC9aZEsrc09IQ2dXWVJKaHBZNw==\"},{\"size\":\"2254814\",\"sha1\":\"d2244cad9bbcf513781afc01f9540138047e5dd7\",\"time\":\"1552597117\",\"def_label\":\"\",\"labels\":{\"ru\":\"Folder Player\"},\"package\":\"com.folderplayerpro\",\"ver_name\":\"4.7\",\"ver_code\":\"183\",\"sdk_version\":\"21\",\"permissions\":[\"android.permission.READ_PHONE_STATE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.MODIFY_AUDIO_SETTINGS\",\"android.permission.BLUETOOTH\",\"android.permission.WAKE_LOCK\",\"android.permission.RECEIVE_BOOT_COMPLETED\"],\"downloads\":\"6\",\"download_time\":\"1552661897\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"d22r13429\",\"android\":\"5.0\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=Und3YlU2WUtURlJIQTF1UktvcXNCU002SVVod2ZIWlcxbkJrUGdQSFJoYlk5YXhDZzc2ektJME01dzNGeGtKSg==\"},{\"size\":\"29097207\",\"sha1\":\"2a1968bd0b63f9037fbc4c51e8657a1bc55b0bdf\",\"time\":\"1552369439\",\"def_label\":\"Lua Player Pro\",\"labels\":{\"ru\":\"Lua плеер Pro\"},\"package\":\"ms.dev.luaplayer_pro\",\"ver_name\":\"1.7.8\",\"ver_code\":\"110000178\",\"sdk_version\":\"16\",\"permissions\":[\"android.permission.SYSTEM_ALERT_WINDOW\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.INTERNET\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.GET_TASKS\",\"android.permission.WAKE_LOCK\",\"android.permission.READ_PHONE_STATE\",\"com.android.vending.CHECK_LICENSE\",\"com.google.android.c2dm.permission.RECEIVE\",\"ms.dev.luaplayer_pro.permission.C2D_MESSAGE\"],\"downloads\":\"14\",\"download_time\":\"1564133089\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"2a1r13309\",\"android\":\"4.1\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=Ti9SZ1F3VXdWQjVWQWt1bG1vZTBiTHRvOU1RbjA1UmlWRG15L3IrcnBrQTBvdUxhZWc4UlN0NjBTSWFrZnViQQ==\"},{\"size\":\"28962757\",\"sha1\":\"0b72decc6c68bbe494b7a79734e64475fe2efd4d\",\"time\":\"1552083036\",\"def_label\":\"Make slideshow with music\",\"labels\":{\"de\":\"Aus einem Foto ein Video mit Musik machen\",\"en\":\"Make slideshow with music\",\"fr\":\"Créer une video à partir des photos avec la musique\",\"es\":\"Hacer un vídeo de fotos con música\",\"ru\":\"Сделать видео из фото с музыкой\",\"ca\":\"Make slideshow with music\",\"da\":\"Make slideshow with music\",\"fa\":\"Make slideshow with music\",\"ja\":\"Make slideshow with music\",\"ka\":\"Make slideshow with music\",\"pa\":\"Make slideshow with music\",\"ta\":\"Make slideshow with music\",\"nb\":\"Make slideshow with music\",\"be\":\"Make slideshow with music\",\"ne\":\"Make slideshow with music\",\"te\":\"Make slideshow with music\",\"af\":\"Make slideshow with music\",\"bg\":\"Make slideshow with music\",\"th\":\"Make slideshow with music\",\"zh\":\"Make slideshow with music\",\"fi\":\"Make slideshow with music\",\"hi\":\"Make slideshow with music\",\"si\":\"Make slideshow with music\",\"vi\":\"Make slideshow with music\",\"kk\":\"Make slideshow with music\",\"mk\":\"Make slideshow with music\",\"sk\":\"Make slideshow with music\",\"uk\":\"Make slideshow with music\",\"el\":\"Make slideshow with music\",\"gl\":\"Make slideshow with music\",\"ml\":\"Make slideshow with music\",\"nl\":\"Make slideshow with music\",\"pl\":\"Make slideshow with music\",\"sl\":\"Make slideshow with music\",\"tl\":\"Make slideshow with music\",\"am\":\"Make slideshow with music\",\"km\":\"Make slideshow with music\",\"bn\":\"Make slideshow with music\",\"in\":\"Make slideshow with music\",\"kn\":\"Make slideshow with music\",\"mn\":\"Make slideshow with music\",\"ko\":\"Make slideshow with music\",\"lo\":\"Make slideshow with music\",\"ro\":\"Make slideshow with music\",\"sq\":\"Make slideshow with music\",\"ar\":\"Make slideshow with music\",\"hr\":\"Make slideshow with music\",\"mr\":\"Make slideshow with music\",\"sr\":\"Make slideshow with music\",\"tr\":\"Make slideshow with music\",\"ur\":\"Make slideshow with music\",\"bs\":\"Make slideshow with music\",\"cs\":\"Make slideshow with music\",\"is\":\"Make slideshow with music\",\"ms\":\"Make slideshow with music\",\"et\":\"Make slideshow with music\",\"it\":\"Make slideshow with music\",\"lt\":\"Make slideshow with music\",\"pt\":\"Make slideshow with music\",\"eu\":\"Make slideshow with music\",\"gu\":\"Make slideshow with music\",\"hu\":\"Make slideshow with music\",\"zu\":\"Make slideshow with music\",\"lv\":\"Make slideshow with music\",\"sv\":\"Make slideshow with music\",\"iw\":\"Make slideshow with music\",\"sw\":\"Make slideshow with music\",\"hy\":\"Make slideshow with music\",\"ky\":\"Make slideshow with music\",\"my\":\"Make slideshow with music\",\"az\":\"Make slideshow with music\",\"uz\":\"Make slideshow with music\",\"en_CA\":\"Make slideshow with music\",\"fr_CA\":\"Créer une video à partir des photos avec la musique\",\"en_GB\":\"Make slideshow with music\",\"en_XC\":\"Make slideshow with music\",\"zh_HK\":\"Make slideshow with music\",\"zh_CN\":\"Make slideshow with music\",\"en_IN\":\"Make slideshow with music\",\"pt_BR\":\"Make slideshow with music\",\"es_US\":\"Hacer un vídeo de fotos con música\",\"pt_PT\":\"Make slideshow with music\",\"en_AU\":\"Make slideshow with music\",\"zh_TW\":\"Make slideshow with music\"},\"package\":\"com.sertanta.slideshowmaker.movie.movieslideshowmaker\",\"ver_name\":\"1.1.7\",\"ver_code\":\"34\",\"sdk_version\":\"19\",\"permissions\":[\"android.permission.RECORD_AUDIO\",\"android.permission.CAMERA\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.INTERNET\",\"com.android.vending.BILLING\",\"android.permission.WAKE_LOCK\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.ACCESS_NETWORK_STATE\",\"com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE\",\"com.google.android.c2dm.permission.RECEIVE\",\"com.sertanta.slideshowmaker.movie.movieslideshowmaker.permission.C2D_MESSAGE\"],\"downloads\":\"19\",\"download_time\":\"1568130847\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"0b7r13181\",\"android\":\"4.4\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=UGVwYlN0MHZ5SUpXaVd6aGNRdUhBOWl4VkRTRFRxeDc3ZElTSlpVZ1RmRXJQcVpjZGd1TUQ0R1FleU1ZcXREbA==\"},{\"size\":\"81678\",\"sha1\":\"39a5f62e2598208809b2f5cd3a015ed0977e3c68\",\"time\":\"1552079852\",\"def_label\":\"Text Memo\",\"labels\":{},\"package\":\"jp.miotti.TextMemoWidget\",\"ver_name\":\"2.6\",\"ver_code\":\"16\",\"sdk_version\":\"7\",\"permissions\":[\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"4\",\"download_time\":\"1552218550\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"39ar13180\",\"android\":\"2.1\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=RFJWa2Fqc0FUVDNZWEpxZjFNbXVRS0NXNHNFemJ3TzlsN0NRRjV3SkFEZGV6b0h1SDZJTUdlMWo0NFhTZXNSeQ==\"},{\"size\":\"7952394\",\"sha1\":\"0e214247a007c837d32b586c05515b320d1d83c7\",\"time\":\"1552077159\",\"def_label\":\"Titanium Backup\",\"labels\":{\"ca\":\"Titanium Backup\",\"da\":\"Titanium Backup\",\"fa\":\"Titanium Backup\",\"ja\":\"Titanium Backup\",\"ka\":\"Titanium Backup\",\"ta\":\"Titanium Backup\",\"be\":\"Titanium Backup\",\"de\":\"Titanium Backup\",\"he\":\"גיבוי טיטניום\",\"bg\":\"Titanium Backup\",\"fi\":\"Titanium Backup\",\"sk\":\"Titanium Backup\",\"uk\":\"Titanium Backup\",\"el\":\"Titanium Backup\",\"nl\":\"Titanium Backup\",\"pl\":\"Titanium Backup\",\"ko\":\"Titanium Backup\",\"no\":\"Titanium Backup\",\"ro\":\"Titanium Backup\",\"fr\":\"Titanium Backup\",\"tr\":\"Titanium Backup\",\"cs\":\"Titanium Backup\",\"es\":\"Titanium Backup\",\"ms\":\"Titanium Backup\",\"it\":\"Titanium Backup\",\"lt\":\"Titanium Backup\",\"pt\":\"Titanium Backup\",\"hu\":\"Titanium Backup\",\"ru\":\"Titanium Backup\",\"sv\":\"Titanium Backup\",\"iw\":\"גיבוי טיטניום\",\"zh_CN\":\"钛备份\",\"pt_BR\":\"Titanium Backup\",\"es_ES\":\"Titanium Backup\",\"zh_TW\":\"Titanium Backup\"},\"package\":\"com.keramidas.TitaniumBackup\",\"ver_name\":\"8.3.1.4\",\"ver_code\":\"412\",\"sdk_version\":\"3\",\"permissions\":[\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_MEDIA_STORAGE\",\"android.permission.WRITE_MEDIA_STORAGE\",\"android.permission.WAKE_LOCK\",\"android.permission.INTERNET\",\"android.permission.RECEIVE_BOOT_COMPLETED\",\"android.permission.VIBRATE\",\"android.permission.READ_PHONE_STATE\",\"android.permission.GET_ACCOUNTS\",\"android.permission.USE_CREDENTIALS\",\"android.permission.ACCESS_NETWORK_STATE\",\"com.android.browser.permission.READ_HISTORY_BOOKMARKS\",\"com.android.browser.permission.WRITE_HISTORY_BOOKMARKS\",\"android.permission.READ_CONTACTS\",\"android.permission.WRITE_CONTACTS\",\"android.permission.ACCESS_WIFI_STATE\",\"android.permission.CHANGE_WIFI_STATE\",\"android.permission.ACCESS_SUPERUSER\",\"android.permission.REQUEST_INSTALL_PACKAGES\",\"com.keramidas.TitaniumBackupAddon.USE_CP\",\"com.android.voicemail.permission.ADD_VOICEMAIL\",\"com.android.voicemail.permission.READ_WRITE_ALL_VOICEMAIL\"],\"downloads\":\"22\",\"download_time\":\"1566464118\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"0e2r13179\",\"android\":\"1.5\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=ZDhMWHN3UUJaUzRsMTY3ZnRQbFQzRGFnMCs4aXdyWDZvd1h3K2djdTF4UGNuSTU0YndqOXpuUUVZM0s2OG9Raw==\"},{\"size\":\"10838995\",\"sha1\":\"b1e4a7948d0339415d9d70b1cf9158246617a554\",\"time\":\"1552074021\",\"def_label\":\"VidTrim Pro\",\"labels\":{\"ru\":\"VidTrim Pro\"},\"package\":\"com.goseet.VidTrimPro\",\"ver_name\":\"2.5.11\",\"ver_code\":\"79\",\"sdk_version\":\"14\",\"permissions\":[\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.WAKE_LOCK\",\"android.permission.INTERNET\",\"android.permission.ACCESS_NETWORK_STATE\",\"com.android.vending.CHECK_LICENSE\",\"com.google.android.c2dm.permission.RECEIVE\",\"com.goseet.VidTrimPro.permission.C2D_MESSAGE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"19\",\"download_time\":\"1556570109\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"b1er13177\",\"android\":\"4.0\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=dEpBTnRGZzNSNTFkVkVuaFVCcnJCR0NhekxJZDZBWEYxSXRIdW5LUFhFTXNVY1N0d0JEbkRtTUFiVndWWVk2WA==\"},{\"size\":\"5218575\",\"sha1\":\"51e39b1a5a65a8345071c8515ca3e3b557ffeb78\",\"time\":\"1552073091\",\"def_label\":\"Kate Mobile\",\"labels\":{\"ru\":\"Kate Mobile\"},\"package\":\"com.perm.kate\",\"ver_name\":\"52.2\",\"ver_code\":\"446\",\"sdk_version\":\"10\",\"permissions\":[\"android.permission.INTERNET\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.VIBRATE\",\"android.permission.ACCESS_COARSE_LOCATION\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.READ_LOGS\",\"android.permission.READ_PHONE_STATE\",\"android.permission.RECORD_AUDIO\",\"android.permission.READ_CONTACTS\",\"android.permission.WRITE_CONTACTS\",\"android.permission.AUTHENTICATE_ACCOUNTS\",\"android.permission.MANAGE_ACCOUNTS\",\"android.permission.GET_ACCOUNTS\",\"android.permission.READ_SYNC_SETTINGS\",\"android.permission.WRITE_SYNC_SETTINGS\",\"android.permission.RECEIVE_BOOT_COMPLETED\",\"android.permission.WAKE_LOCK\",\"com.android.launcher.permission.INSTALL_SHORTCUT\",\"android.permission.USE_FINGERPRINT\",\"android.permission.MODIFY_AUDIO_SETTINGS\",\"com.perm.kate.permission.C2D_MESSAGE\",\"com.google.android.c2dm.permission.RECEIVE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"39\",\"download_time\":\"1552635822\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"51er13176\",\"android\":\"2.3.3\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=WFFLNlVXMk10S1VLWlFRUEFVb3IycnNKTWZSOWR5Yk1tQzBYWnMxU2ZzNEx0UHVxNkQ3Z2RiRGF1QjFnMmllaw==\"},{\"size\":\"11101926\",\"sha1\":\"f2b594385c4980d3d6657d82c0514de8a734e109\",\"time\":\"1552072653\",\"def_label\":\"APK Editor Pro\",\"labels\":{\"ru\":\"APK Editor Pro\",\"uk\":\"APK Editor Pro\"},\"package\":\"com.gmail.heagoo.apkeditor.pro\",\"ver_name\":\"1.9.10\",\"ver_code\":\"120\",\"sdk_version\":\"14\",\"permissions\":[\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.REQUEST_INSTALL_PACKAGES\",\"android.permission.INTERNET\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"33\",\"download_time\":\"1567071094\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"f2br13175\",\"android\":\"4.0\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=b3FqNWtST2JwclpZTVlkLzVuQzk0Ym1HOXIzUndBMDVvVE1Ea1JvQkJuenBIZWRVL2dHNTVzNUlZU1pwSjdDTQ==\"},{\"size\":\"3876747\",\"sha1\":\"ecba33cca8de3e84e56a1cf823fcdc0f357e5183\",\"time\":\"1552044139\",\"def_label\":\"AdAway\",\"labels\":{\"ru\":\"AdAway\"},\"package\":\"org.adaway\",\"ver_name\":\"4.2.3\",\"ver_code\":\"40203\",\"sdk_version\":\"16\",\"permissions\":[\"android.permission.INTERNET\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.WAKE_LOCK\",\"android.permission.RECEIVE_BOOT_COMPLETED\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"26\",\"download_time\":\"1554419289\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"ecbr13168\",\"android\":\"4.1\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=STlDMzhXM0J2VXJzZVpzS1IvN204MHRDdW5Fa2RiM2NPaXQvaHI2aFRzeS9xVmVsdk83WDUzdDV4YzRBbjhKNQ==\"},{\"size\":\"13144989\",\"sha1\":\"d696b28b09a3e63e7dc35608e13abfa05a3d5040\",\"time\":\"1551966252\",\"def_label\":\"SharikDC\",\"labels\":{\"ca\":\"SharikDC\",\"da\":\"SharikDC\",\"fa\":\"SharikDC\",\"ja\":\"SharikDC\",\"nb\":\"SharikDC\",\"de\":\"SharikDC\",\"af\":\"SharikDC\",\"bg\":\"SharikDC\",\"th\":\"SharikDC\",\"fi\":\"SharikDC\",\"hi\":\"SharikDC\",\"vi\":\"SharikDC\",\"sk\":\"SharikDC\",\"uk\":\"SharikDC\",\"el\":\"SharikDC\",\"nl\":\"SharikDC\",\"pl\":\"SharikDC\",\"sl\":\"SharikDC\",\"tl\":\"SharikDC\",\"am\":\"SharikDC\",\"in\":\"SharikDC\",\"ko\":\"SharikDC\",\"ro\":\"SharikDC\",\"ar\":\"SharikDC\",\"fr\":\"SharikDC\",\"hr\":\"SharikDC\",\"sr\":\"SharikDC\",\"tr\":\"SharikDC\",\"cs\":\"SharikDC\",\"es\":\"SharikDC\",\"it\":\"SharikDC\",\"lt\":\"SharikDC\",\"pt\":\"SharikDC\",\"hu\":\"SharikDC\",\"ru\":\"SharikDC\",\"zu\":\"SharikDC\",\"lv\":\"SharikDC\",\"sv\":\"SharikDC\",\"iw\":\"SharikDC\",\"sw\":\"SharikDC\",\"bs_BA\":\"SharikDC\",\"fr_CA\":\"SharikDC\",\"lo_LA\":\"SharikDC\",\"en_GB\":\"SharikDC\",\"bn_BD\":\"SharikDC\",\"et_EE\":\"SharikDC\",\"ka_GE\":\"SharikDC\",\"ky_KG\":\"SharikDC\",\"km_KH\":\"SharikDC\",\"zh_HK\":\"SharikDC\",\"si_LK\":\"SharikDC\",\"mk_MK\":\"SharikDC\",\"ur_PK\":\"SharikDC\",\"sq_AL\":\"SharikDC\",\"hy_AM\":\"SharikDC\",\"my_MM\":\"SharikDC\",\"zh_CN\":\"SharikDC\",\"pa_IN\":\"SharikDC\",\"ta_IN\":\"SharikDC\",\"te_IN\":\"SharikDC\",\"ml_IN\":\"SharikDC\",\"en_IN\":\"SharikDC\",\"kn_IN\":\"SharikDC\",\"mr_IN\":\"SharikDC\",\"gu_IN\":\"SharikDC\",\"mn_MN\":\"SharikDC\",\"ne_NP\":\"SharikDC\",\"pt_BR\":\"SharikDC\",\"gl_ES\":\"SharikDC\",\"eu_ES\":\"SharikDC\",\"is_IS\":\"SharikDC\",\"es_US\":\"SharikDC\",\"pt_PT\":\"SharikDC\",\"en_AU\":\"SharikDC\",\"zh_TW\":\"SharikDC\",\"be_BY\":\"SharikDC\",\"ms_MY\":\"SharikDC\",\"az_AZ\":\"SharikDC\",\"kk_KZ\":\"SharikDC\",\"uz_UZ\":\"SharikDC\"},\"package\":\"sharikdc.sharikdc\",\"ver_name\":\"1.0.1\",\"ver_code\":\"3\",\"sdk_version\":\"21\",\"permissions\":[\"com.android.vending.CHECK_LICENSE\",\"android.permission.INTERNET\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"downloads\":\"8\",\"download_time\":\"1567506385\",\"user_id\":\"274268\",\"file_status\":\"0\",\"moderator_id\":\"0\",\"status_reason\":null,\"status_time\":\"0\",\"app_id\":\"d69r13113\",\"android\":\"5.0\",\"icon\":\"http://appsend.store/api/icon.php?v=1&hash=QThNQmhUNlhTUEtIMDB1MWVsR0l2WlhXd0kzMzRYbjhyeEswUGdOM0VhNUlXTTFuWGROOGpGY3JEUUdpR245QQ==\"}],\"status\":200}";
            String OriginalString = "Абракадабра";

            new DataOutputStream(bytes).writeUTF(OriginalString);
            bytes.flush();
            HexUtil.dump_(bytes.toByteArray(), "original ");
            bytes.reset();

            input = new ByteArrayInputStream(OriginalString.getBytes(StandardCharsets.UTF_8));
            lzw_compression.lzw_compress(input, new DataOutputStream(bytes));
            //bytes.flush();
            System.out.println(bytes.size() + " 1 bytes, " + bytes.toString());
            input.close();

            bytes.reset();
            LZWOutputStream lzw = new LZWOutputStream(bytes);
            lzw.write(OriginalString.getBytes(StandardCharsets.UTF_8));
            lzw.flush();
            lzw.close();
            System.out.println(bytes.size() + " 2 bytes, " + bytes.toString());

            input = new ByteArrayInputStream(bytes.toByteArray());
            bytes.reset();
            lzw_compression.lzw_extract(input, bytes);
            bytes.flush();
            HexUtil.dump_(bytes.toByteArray(), "decoded  ");
            System.out.println(bytes.size() + " 3 bytes, " + new String(bytes.toByteArray(), StandardCharsets.UTF_8));
            System.out.println(bytes.size() + " 4 bytes, " + OriginalString);
            System.out.println(new String(bytes.toByteArray(), StandardCharsets.ISO_8859_1).equals(OriginalString) ? "passed" : "failed");
            bytes.reset();

            OutputStream gzip = new GZIPOutputStream(bytes);
            gzip.write(OriginalString.getBytes(StandardCharsets.UTF_8));
            gzip.flush();
            gzip.close();
            System.out.println(bytes.size() + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
