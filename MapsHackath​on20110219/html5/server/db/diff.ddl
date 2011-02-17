------------------------------------------------------------------
-- DataNucleus SchemaTool (version 2.2.2) ran at 16/02/2011 23:11:10
------------------------------------------------------------------
-- Schema diff for jdbc:mysql://127.0.0.1/worldrescue and the following classes:-
--     org.kyoto_gtug.savetheworld.domain.Help
--
-- Table `HELP` for classes [org.kyoto_gtug.savetheworld.domain.Help]
ALTER TABLE `HELP` ADD COLUMN `DATE` BIGINT NULL;

-- Constraints for table `HELP` for class(es) [org.kyoto_gtug.savetheworld.domain.Help]
CREATE INDEX `HELP_DATE_IDX` ON `HELP` (`DATE`);



------------------------------------------------------------------
-- Sequences and SequenceTables
