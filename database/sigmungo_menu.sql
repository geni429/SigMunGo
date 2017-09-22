-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sigmungo
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `contentid` varchar(20) DEFAULT NULL,
  `menu` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('2kknKoVXkS','과일'),('2kknKoVXkS','과일샐러드'),('2kknKoVXkS','프라이드 치킨+감자'),('2kknKoVXkS','모듬 치즈'),('2kknKoVXkS','칠리 새우'),('2kknKoVXkS','중화풍 쇠고기 요리'),('2kknKoVXkS','찹 스테이크'),('2kknKoVXkS','케이준 샐러드'),('2kknKoVXkS','모듬 소시지'),('2kknKoVXkS','해산물 볶음 우동'),('2kknKoVXkS','골뱅이'),('2kknKoVXkS','돈까스 안주'),('2kknKoVXkS','치킨 커틀릿'),('2kknKoVXkS','감자튀김'),('2kknKoVXkS','감자투김'),('2kknKoVXkS','마른안주'),('2kknKoVXkS','한치'),('2kknKoVXkS','육포'),('2kknKoVXkS','오징어'),('2kknKoVXkS','나쵸'),('2kknKoVXkS','까르보나라 떡볶이'),('2kknKoVXkS','아라비아 떡볶이'),('2kknKoVXkS','오뎅탕'),('2kknKoVXkS','얼큰 뚝배기'),('2kknKoVXkS','밀러 생맥주'),('2kknKoVXkS','카스'),('2kknKoVXkS','카프리'),('2kknKoVXkS','버드 와이저'),('2kknKoVXkS','호가든'),('2kknKoVXkS','칭타오'),('2kknKoVXkS','코로나'),('2kknKoVXkS','하이네켄'),('2kknKoVXkS','산미구엘'),('2kknKoVXkS','아사히'),('2kknKoVXkS','기네스'),('2kknKoVXkS','소주'),('2kknKoVXkS','발렌타인 17년산'),('2kknKoVXkS','발렌타인 15년산'),('2kknKoVXkS','제이앤비 리저브'),('2kknKoVXkS','원저 17년산'),('2kknKoVXkS','원저 12년산'),('2kknKoVXkS','스카치 블루 17년산'),('2kknKoVXkS','스카치 블루 12년산'),('2kknKoVXkS','잭 다니엘'),('2kknKoVXkS','맥켈란 12년산'),('2kknKoVXkS','글렌피딕 15년산'),('2kknKoVXkS','뜨리벤또 골드 리져브 말백'),('2kknKoVXkS','몬테스 알파 쉬라'),('2kknKoVXkS','1865 카버네 쇼비뇽'),('2kknKoVXkS','몬테스 알파 카버네 쇼비뇽'),('2kknKoVXkS','에스쿠도 로호'),('2kknKoVXkS','무릉 까데'),('2kknKoVXkS','샤또 말바'),('2kknKoVXkS','켄달 잭슨 카버네 쇼비뇽'),('2kknKoVXkS','빈 555 쉬라즈'),('2kknKoVXkS','미션드 렝고 카베네 쇼비뇽'),('2kknKoVXkS','켄달 잭슨 샤도네이'),('2kknKoVXkS','빌라 엠'),('2kknKoVXkS','킴크로포트 말보로쇼비뇽블랑'),('2kknKoVXkS','산타 리타 120 샤도네이'),('2kknKoVXkS','The Terrace 하우스 와인'),('2kknKoVXkS','에스프레소'),('2kknKoVXkS','아포카토'),('2kknKoVXkS','아메리카노(hot)'),('2kknKoVXkS','아메리카노(ice)'),('2kknKoVXkS','카푸치노'),('2kknKoVXkS','카라멜 마키아또'),('2kknKoVXkS','핫쵸코'),('2kknKoVXkS','카페 라떼'),('2kknKoVXkS','연유 라떼'),('2kknKoVXkS','카페모카 라떼'),('2kknKoVXkS','바닐라 라떼'),('2kknKoVXkS','고구마 라떼'),('2kknKoVXkS','녹차라떼'),('2kknKoVXkS','모카 프라페'),('2kknKoVXkS','카라멜 프라페'),('2kknKoVXkS','볼로네제(미트 파스타)'),('2kknKoVXkS','페스카토레'),('2kknKoVXkS','아라비아따 파스타'),('2kknKoVXkS','모짜렐라 오븐 파스타'),('2kknKoVXkS','해산물 로제 파스타'),('2kknKoVXkS','게살 로제 파스타'),('2kknKoVXkS','페스카토레'),('2kknKoVXkS','까르보나라'),('2kknKoVXkS','고르곤 졸라 파스타'),('2kknKoVXkS','풍기 파스타'),('2kknKoVXkS','명란 크림 파스타'),('2kknKoVXkS','알리오 올리오'),('2kknKoVXkS','봉골레 파스타'),('2kknKoVXkS','해산물 오일 파스타'),('2kknKoVXkS','뚝배기 파스타'),('2kknKoVXkS','오징어 먹물 파스타'),('2kknKoVXkS','콜라'),('2kknKoVXkS','사이다'),('2kknKoVXkS','복숭아아이스티'),('2kknKoVXkS','레몬'),('2kknKoVXkS','자몽'),('2kknKoVXkS','와인'),('2kknKoVXkS','유자 스무디'),('2kknKoVXkS','딸기 스무디'),('2kknKoVXkS','블루베리 스무디'),('2kknKoVXkS','요거트 수무디'),('2kknKoVXkS','파인애플 주스'),('2kknKoVXkS','키위주스'),('2kknKoVXkS','토마토주스'),('2kknKoVXkS','자몽 주스'),('2kknKoVXkS','오렌지 주스'),('2kknKoVXkS','레몬차'),('2kknKoVXkS','유자차'),('2kknKoVXkS','모과차'),('2kknKoVXkS','대추차'),('2kknKoVXkS','생강차'),('2kknKoVXkS','자몽차'),('2kknKoVXkS','카모마일, 라벤더, 쟈스민 페퍼민트'),('2kknKoVXkS','홍차'),('2kknKoVXkS','녹차'),('2kknKoVXkS','새우 필라프'),('2kknKoVXkS','김치 베이컨 필라프'),('2kknKoVXkS','버섯 덮밥'),('2kknKoVXkS','해산물 덮밥'),('2kknKoVXkS','과해산물 크림 리조또'),('2kknKoVXkS','오징어 먹물 리조또'),('2kknKoVXkS','명란 리조또'),('2kknKoVXkS','버섯 그릴 샐러드'),('2kknKoVXkS','안심 샐러드'),('2kknKoVXkS','연어 샐러드'),('2kknKoVXkS','닭 가슴살 샐러드'),('2kknKoVXkS','리코타 치즈 샐러드'),('2kknKoVXkS','과안심 스테이크'),('2kknKoVXkS','연어 스테이크'),('2kknKoVXkS','치킨 스테이크'),('2kknKoVXkS','함박 스테이크'),('2kknKoVXkS','돈까스');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-23  1:37:56
