public class Main {
    public static void main(String[] args) {

        Odev2Fonksiyonlar deneme = new Odev2Fonksiyonlar();


        //1. kilometer mil

        System.out.println("kilometre mil çevirme = " + deneme.kilometreToMile(12));

        //2. dikdörtgen alanı

        deneme.dikdortgenAlani(12,5);

        //3. faktöriyel
        System.out.println("faktöriyel : " + deneme.faktoriyelhesaplama(-2));

        //4. kaç e var
        deneme.eSayisi("araba");

        //5 iç açi hesaplama

        System.out.println("iç açı : " +deneme.icAciHesaplama(5));

        //6. maaş hesabı
        System.out.println(deneme.maashesaplama(30));

        //7 otopark ücreti
        System.out.println("ototpark ücreti : " +deneme.otoparkUcreti(10));


    }
}