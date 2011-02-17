------------------------------------------------------------------
-- DataNucleus SchemaTool (version 2.2.2) ran at 14/02/2011 00:47:47
------------------------------------------------------------------
-- Schema diff for jdbc:mysql://127.0.0.1/worldrescue and the following classes:-
--     org.kyoto_gtug.savetheworld.domain.Help
--
-- Table `HELP` for classes [org.kyoto_gtug.savetheworld.domain.Help]
CREATE TABLE `HELP`
(
    `ID` BIGINT NOT NULL AUTO_INCREMENT,
    `LAT` DOUBLE NULL,
    `LNG` DOUBLE NULL,
    `MESSAGE` VARCHAR(256) BINARY NULL,
    CONSTRAINT `HELP_PK` PRIMARY KEY (`ID`)
) ENGINE=INNODB;

-- Constraints for table `HELP` for class(es) [org.kyoto_gtug.savetheworld.domain.Help]


------------------------------------------------------------------
-- Sequences and SequenceTables
