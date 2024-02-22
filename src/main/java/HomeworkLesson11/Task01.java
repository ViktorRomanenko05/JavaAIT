package HomeworkLesson11;

import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {

        String toyotaHistory = getToyota();
        String toyota9000 = ToyotaPer1();
        String toyota0010 = ToyotaPer2();
        String toyota1024 = ToyotaPer3();

        String volksHistory = getVolks();
        String volks9000 = VolksPer1();
        String volks0010 = VolksPer2();
        String volks1024 = VolksPer3();

        String fordHistory = getFord();
        String ford9000 = FordPer1();
        String ford0010 = FordPer2();
        String ford1024 = FordPer3();

        String kiaHistory = getKia();
        String kia9000 = KiaPer1();
        String kia0010 = KiaPer2();
        String kia1024 = KiaPer3();

        String choice = "1";

        Scanner scanner = new Scanner(System.in);

        do {

            boolean validInput = false;
            int range = 0;
            int year = 0;
            String manufacturer;
            String country;

            System.out.println("Здравствуйте!\n" +
                    "В данной программе собрана краткая история 4 автомобильных брендов,\n" +
                    "являющихся одними из наиболее популярных в мире\n" +
                    "за 1990 - 2024 г.г.:");
            System.out.println("   - Toyota\n" +
                    "   - Volkswagen\n" +
                    "   - Ford\n" +
                    "   - Kia\n");

            System.out.println("Пожалуйста, введите интересующую марку автомобиля:");

            manufacturer = scanner.next().toLowerCase();

            while (!validInput) {

                System.out.println("Введите интересующий год от 1990 до 2024: ");
                if (scanner.hasNextInt()) {
                    year = scanner.nextInt();
                    validInput = true;
                } else {
                    System.out.println("Ошибка: Введено не верное значение.\n" +
                            "Введите год корректно");
                    scanner.next();
                }

            }


            country = switch (manufacturer) {
                case "toyota" -> "Япония";
                case "volkswagen" -> "Германия";
                case "ford" -> "США";
                case "kia" -> "Корея";
                default -> "Ошибка, страна не определена";
            };

            if (year >= 1990 && year <= 2000) {
                range = 1;
            } else if (year > 2000 && year <= 2010) {
                range = 2;
            } else if (year > 2010 && year <= 2024) {
                range = 3;
            } else {
                System.out.println("\nОшибка! Год задан не верно\n" +
                        "Введите год в диапазоне 1990 - 2024,\n" +
                        "чтобы увидеть историю марки в выбранный период.\n" +
                        "А пока можете ознакомиться с общей историей бренда.");
            }


            if (manufacturer.equals("toyota")) {

                System.out.println("\nTOYOTA\n");
                System.out.println("Страна производства: " + country + "\n");
                System.out.println(toyotaHistory + "\n");

                switch (range) {
                    case 1:
                        System.out.println(toyota9000);
                        break;
                    case 2:
                        System.out.println(toyota0010);
                        break;
                    case 3:
                        System.out.println(toyota1024);
                        break;
                }
            } else if (manufacturer.equals("volkswagen")) {

                System.out.println("\nVOLKSWAGEN\n");
                System.out.println("Страна производства: " + country + "\n");
                System.out.println(volksHistory + "\n");

                switch (range) {
                    case 1:
                        System.out.println(volks9000);
                        break;
                    case 2:
                        System.out.println(volks0010);
                        break;
                    case 3:
                        System.out.println(volks1024);
                        break;
                }
            } else if (manufacturer.equals("ford")) {

                System.out.println("\nFORD\n");
                System.out.println("Страна производства: " + country + "\n");
                System.out.println(fordHistory + "\n");

                switch (range) {
                    case 1:
                        System.out.println(ford9000);
                        break;
                    case 2:
                        System.out.println(ford0010);
                        break;
                    case 3:
                        System.out.println(ford1024);
                        break;
                }
            } else if (manufacturer.equals("kia")) {

                System.out.println("\nKIA\n");
                System.out.println("Страна производства: " + country + "\n");
                System.out.println(kiaHistory + "\n");

                switch (range) {
                    case 1:
                        System.out.println(kia9000);
                        break;
                    case 2:
                        System.out.println(kia0010);
                        break;
                    case 3:
                        System.out.println(kia1024);
                        break;
                }
            } else {
                System.out.println("\nВы не верно ввели марку автомобиля\n");
            }

            System.out.println("\nДля продолжения работы введите 1,\n" +
                    "для завершения - любое другое значение");

            choice = scanner.next();


        } while (choice.equals("1"));

        scanner.close();
    }

    public static String getToyota(){
        String toyotaHist = "Компания Toyota, основанная в 1937 году Киичиро Тойода,\n" +
                "изначально была отделением Toyoda Automatic Loom Works, занимающимся\n" +
                "производством ткацких станков, под руководством его отца, Сакичи Тойоды.\n" +
                "Переход к производству автомобилей был мотивирован желанием Киичиро\n" +
                "диверсифицировать бизнес семьи и способствовать индустриализации Японии.\n" +
                "\n";
        return toyotaHist;
    }

    public static String ToyotaPer1(){
        String toyotaP1 = "1990-е: Глобализация и инновации\n" +
                "Расширение в Северной Америке: Toyota укрепила своё присутствие в Северной Америке,\n" +
                "открыв новые заводы и расширяя производство в регионе. Это помогло компании укрепить\n" +
                "свои позиции на крупнейшем автомобильном рынке мира.\n" +
                "Внедрение новых технологий: Toyota продолжала внедрять инновационные технологии,\n" +
                "включая усовершенствования в области безопасности и эффективности двигателей.\n" +
                "Рост экологической ответственности: В конце 1990-х Toyota начала сосредотачиваться\n" +
                "на разработке экологически чистых автомобилей.\n" +
                "В 1997 году был представлен Toyota Prius — первый в мире массово производимый\n" +
                "гибридный автомобиль, который стал символом направления компании на создание более\n" +
                "экологически чистых транспортных средств.";

        return toyotaP1;
    }

    public static String ToyotaPer2() {
        String toyotaP2 = "В период с 2000 по 2010 год Toyota укрепила свои позиции\n" +
                "как один из ведущих мировых автопроизводителей, в значительной степени благодаря успеху\n" +
                "своих гибридных моделей, включая популярный Toyota Prius.\n" +
                "Компания также столкнулась с вызовами, включая масштабные отзывы автомобилей из-за проблем\n" +
                "с безопасностью.";

        return  toyotaP2;
    }

    public static String ToyotaPer3(){
        String toyotaP3 = "С 2010 по 2024 год Toyota активно развивала технологии электромобилей\n" +
                "и автономного вождения, укрепляя свою репутацию в области инноваций и устойчивого развития.\n" +
                "Компания также расширила своё присутствие на глобальных рынках, несмотря на возрастающую\n" +
                "конкуренцию и вызовы, связанные с мировыми экономическими и экологическими изменениями.";

        return toyotaP3;
    }

    public static String getVolks(){
        String volksHist = "Фольксваген, основанный в 1937 году, к 21-му веку превратился в одного из крупнейших\n" +
                "мировых автопроизводителей, активно развивая линейку электромобилей и инновационные технологии.\n" +
                "Компания столкнулась с серьёзным испытанием в виде скандала с дизельными двигателями в 2015 году,\n" +
                "но продолжила адаптацию к меняющимся рыночным и экологическим требованиям.";
        return volksHist;
    }

    public static String VolksPer1(){
        String volksP1 = "В 1990-е годы Volkswagen сосредоточилась на глобальной экспансии и диверсификации своего\n" +
                "портфолио, приобретя бренды, такие как Skoda и Bentley, и укрепив своё присутствие на международных\n" +
                "рынках. Этот период также ознаменовался усилиями по улучшению качества и инновационности продукции,\n" +
                "что способствовало росту продаж и репутации компании.";
        return volksP1;
    }

    public static String VolksPer2(){
        String volksP2 = "С 2000 по 2010 год Volkswagen активно расширял своё портфолио, запуская ключевые модели,\n" +
                "такие как Touareg и Passat CC, и укрепляя свои позиции в Китае, ставшем крупнейшим рынком компании.\n" +
                "В это десятилетие VW также сделал значительный акцент на развитии экологически чистых технологий,\n" +
                "включая гибридные и электрические автомобили.";
        return volksP2;
    }

    public static String VolksPer3(){
        String volksP3 = "С 2010 по 2024 год Volkswagen активно развивал электрические и гибридные технологии,\n" +
                "запуская значимые модели, такие как ID.3, и стремясь к лидерству в сегменте электромобилей. В этот\n" +
                "период компания также стремилась восстановить свою репутацию после скандала с дизельными двигателями\n" +
                "в 2015 году, сосредоточив внимание на устойчивом развитии и инновациях.";
        return volksP3;
    }

    public static String getFord(){
        String fordHist = "Ford Motor Company, основанная Генри Фордом в 1903 году, революционизировала автомобильную\n" +
                "промышленность внедрением линейного производства, делая автомобили доступными для широких масс.\n" +
                "В последующие десятилетия компания продолжала инновационные разработки, расширяя свой глобальный\n" +
                "охват и адаптируясь к изменениям рынка с акцентом на устойчивость и электромобили.";
        return fordHist;
    }

    public static String FordPer1(){
        String fordP1 = "В 1990-е годы Ford Motor Company активно расширяла свой глобальный охват, приобретая такие\n" +
                "компании, как Jaguar и Volvo, и укрепляя своё присутствие на международных рынках.\n" +
                "Этот период также ознаменовался интенсивной работой над инновациями в области безопасности и\n" +
                "эффективности топливопотребления, включая разработку и внедрение новых технологий и моделей\n" +
                "автомобилей.";
        return fordP1;
    }

    public static String FordPer2(){
        String fordP2 = "В период с 2000 по 2010 год Ford Motor Company столкнулась с серьезными вызовами,\n" +
                "включая экономический спад и мировой финансовый кризис, но смогла адаптироваться,\n" +
                "сосредоточив усилия на улучшении качества, эффективности и разработке инновационных продуктов,\n" +
                "таких как гибридные автомобили. Компания также провела реструктуризацию, сократив расходы и\n" +
                "сфокусировавшись на своих основных брендах, что позволило ей восстановить финансовую стабильность\n" +
                "без прямой государственной поддержки.";
        return fordP2;
    }

    public static String FordPer3(){
        String fordP3 = "С 2010 по 2024 год Ford ускорил свои инновационные инициативы, сделав ставку на\n" +
                "электрификацию, цифровые технологии и автономное вождение. Компания значительно расширила\n" +
                "свой портфель электромобилей, представив модели, такие как полностью электрический\n" +
                "Mustang Mach-E и электрический пикап F-150 Lightning, подчеркивая свою приверженность устойчивому\n" +
                "развитию и ответственному подходу к будущему автомобилестроения.";
        return fordP3;
    }

    public static String getKia(){
        String kiaHist = "Kia, основанная в 1944 году в Южной Корее, первоначально производила стальные трубы и\n" +
                "велосипедные детали, прежде чем переключиться на автомобилестроение в 1960-х годах. С тех пор\n" +
                "компания превратилась в одного из крупнейших мировых автопроизводителей с широким ассортиментом\n" +
                "моделей, включая седаны, внедорожники и минивэны.";
        return kiaHist;
    }

    public static String KiaPer1(){
        String kiaP1 = "В 1990-е годы Kia столкнулась с финансовыми трудностями, которые кульминировали в объявлении\n" +
                "о банкротстве в 1997 году, но вскоре после этого компания вошла в состав Hyundai Motor Group,\n" +
                "что помогло стабилизировать её положение и расширить международное присутствие.";
        return kiaP1;
    }

    public static String KiaPer2(){
        String kiaP2 = "В период с 2000 по 2010 год Kia активно работала над улучшением качества и дизайна своих\n" +
                "автомобилей, что привело к значительному увеличению продаж и укреплению бренда на глобальном рынке.\n" +
                "Особенно успешным стало внедрение нового корпоративного стиля,\n" +
                "разработанного известным дизайнером Питером Шрайером.";
        return kiaP2;
    }

    public static String KiaPer3(){
        String kiaP3 = "С 2010 года Kia продолжила своё стремление к инновациям, активно внедряя передовые технологии\n" +
                "и электрификацию своего модельного ряда. Компания значительно расширила своё присутствие на рынке\n" +
                "электромобилей и гибридов, представив такие модели, как Kia Niro и Kia EV6, и подтвердила свою\n" +
                "репутацию как бренда, ориентированного на будущее автомобилестроения.";
        return kiaP3;
    }



}
