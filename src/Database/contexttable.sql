-- --------------------------------------------------------
-- ホスト:                          localhost
-- サーバーのバージョン:                   5.7.32-log - MySQL Community Server (GPL)
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- noukin_jyuku のデータベース構造をダンプしています
CREATE DATABASE IF NOT EXISTS `noukin_jyuku` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `noukin_jyuku`;

--  テーブル noukin_jyuku.contexttable の構造をダンプしています
CREATE TABLE IF NOT EXISTS `contexttable` (
  `grade` int(11) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- テーブル noukin_jyuku.contexttable: ~12 rows (約) のデータをダンプしています
INSERT INTO `contexttable` (`grade`, `target`) VALUES
	(11, '小学1年生'),
	(12, '小学2年生'),
	(13, '小学3年生'),
	(14, '小学4年生'),
	(15, '小学5年生'),
	(16, '小学6年生'),
	(21, '中学1年生'),
	(22, '中学2年生'),
	(23, '中学3年生'),
	(31, '高校1年生'),
	(32, '高校2年生'),
	(33, '高校3年生');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
