public class Odev2Fonksiyonlar {

    // 1. Kilometre mile dönüştürme
    public double kilometreToMile(double  kilometre){
        return kilometre * 0.621;
    }

    // 2. dikdörtgen alanı hesaplama
    public void dikdortgenAlani(int uzunKenar,int kisaKenar){
        System.out.println("kenarları "+uzunKenar + "ve "+kisaKenar + "olan dikdörtgenin alanı = " + uzunKenar*kisaKenar);
    }

    //3. Faktöriyel  bulma
    public int faktoriyelhesaplama(int sayi){
        if (sayi < 0){
            return -1;
        }else if (sayi ==0 ){
            return 1;
        }else {
            int sonuc = 1;
            for (int i = 1; i<sayi+1 ; i++){
                sonuc *= i;
            }
            return sonuc;
        }

    }

    //4. kelime içinde e bulma
    public void eSayisi (String kelime) {
        int sayac = 0;
        for (int i = 0; i < kelime.length(); i++) {
            char harf = kelime.charAt(i);
            if (harf == 'E' || harf == 'e') {
                sayac++;
            }


        }
        System.out.println(kelime + " kelimesinde toplam " + sayac + " adet E harfi bulunmaktadır.");
    }

    //5. iç açı hesaplama
    public int icAciHesaplama(int kenarSayisi){
        if (kenarSayisi < 3) {
            return 0; // Geçersiz kenar sayısı
        }
        return (kenarSayisi - 2) * 180 /kenarSayisi;
    }

    //6 maaş hesabı
    public String maashesaplama(int gunSayisi){
        int maas = 0;
        int calismaSaati = gunSayisi * 8;
        if (calismaSaati < 150){
            maas = calismaSaati * 40 ;
            return "maaşınız = " +maas;
        }else {
           maas = ((calismaSaati -150 )*80 ) + (150*40);
           return  "maaşınız = " +maas;
        }

    }

    //7 otopark ücreti
    public int otoparkUcreti(int sure){
        int ucret =0;
        if (sure <=1){
            return ucret = 50;
        }else {
            return ucret = 50 + (sure-1)*10;
        }
    }
}
