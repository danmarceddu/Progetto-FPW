-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              5.7.19 - MySQL Community Server (GPL)
-- S.O. server:                  Win64
-- HeidiSQL Versione:            9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dump dei dati della tabella fastpresswriter.articles: ~0 rows (circa)
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` (`articleId`, `authorId`, `title`, `imageURL`, `date`, `articleText`, `articleCategory`) VALUES
	(1, 3, 'Formula1 Libere1, pioggia a Monza: stop per Vettel; 2° Raikkonen', 'https://pbs.twimg.com/media/Dl6_qjZW0AAocNt.jpg', '2018-07-08', 'Pioggia a Monza per la prima sessione di libere del GP d\'Italia, con Perez che segna il miglior tempo davanti a Raikkonen e Ocon, mentre Vettel annaspa frenato da problemi al cambio. Il circuito brianzolo, inzuppato d\'acqua fin dalle prime ore del mattino - condizioni che potrebbero anche arrivare al sabato mattina - vede per ora brillare le Force India, accreditate del 1° e 3° tempo, mentre la Ferrari piazza Kimi al secondo posto e Vettel al 17°, rallentato da un inconveniente al cambio.\r\n\r\nUna perdita alla trasmissione, infatti, ha interrotto prematuramente la prima sessione di Vettel dopo circa 50 minuti: via la tuta e attesa per la seconda sessione che Seb disputerà con un nuovo cambio, lo stesso che porterà in gara. Inconveniente che rallenta il lavoro del tedesco, ma non ne compromette la posizione in griglia: quella interessata era una componente a fine vita e non quella da impiegare in corsa, la cui sostituzione non comporta quindi penalità. Saranno invece zavorrati dalla partenza in ultima fila Ricciardo e Hulkenberg.\r\n\r\nInizio prudente per tutti, con alternanza di gomme intermedie e full wet per saggiare le condizioni della pista. Qualche dritto qui e là, con un numero di Verstappen, bravissimo a gestire la sua Red Bull alla Parabolica dopo una sbandata controllata di grande effetto. Poi la pista migliora, si velocizza, e i tempi iniziano a scendere. Nel finale ecco i crono più rilevanti con il guizzo di Perez, confermato in Force India per la prossima stagione, che stacca il tempo di 1\'34"000 davanti a Raikkonen (+0"550), Ocon (+0"593), Hartley, Ricciardo, Gasly e Verstappen. 10° Bottas e 11° Hamilton, ma con soli 6 giri all\'attivo. Alle 15 la seconda sessione.', 'Sport'),
	(2, 3, 'Asia Nuccetelli, matrimonio in vista! Il fidanzato Gianfranco Battistini le ha dato l’anello', 'http://static2.oggi.it/wp-content/uploads/sites/5/2018/08/asia-nuccetelli-sposa-gianfranco-battistini-645.jpg?v=1535540203', '2018-08-31', 'La romantica proposta, l’anello, i baci: la figlia di Antonella Mosetti ha detto sì. “Tra poco meno di un anno sarò sua moglie”, scrive sui social. “Non riesco a esprimere la mia felicità”\r\n\r\nAsia Nuccetelli ha detto “sì” al fidanzato Gianfranco Battistini. Lui le ha fatto la proposta durante la loro vacanza, le ha regalato l’anello e lei non ha avuto dubbi: l’ex gieffina vip (in coppia con mamma Antonella Mosetti) è innamorata pazza e super sexy.\r\n\r\nHA CAMBIATO TUTTO - In poco più di un un anno, la figlia 22enne di Antonella Mosetti (GUARDA) ha cambiato tutto: l’aspetto fisico (guarda la sua incredibile trasformazione: FOTO) e il suo stato: da single a fidanzata a… quasi moglie. I preparativi per il matrimonio sono già cominciati. E dopo il “sì”, ecco lo scambio delle dediche romantiche dei promessi sposi via social e il balletto di coppia.\r\n\r\n“MIA REGINA PER L’ETERNITÀ” - “Mia regina per l’eternità. Ogni mio attimo sarà tuo… ogni tuo problema sarà il mio”, cinguetta lui. “Ho detto SI…”, racconta lei. “Tra poco meno di un anno sarò sua moglie. Non riesco a esprimere la mia felicità (…). So che non sarà facile, questa vita mai lo è, ma so anche che mano nella mano tutto sarà più semplice. E allora BENVENUTO amore mio, benvenuto in questa vita accanto a me. Ti amo e ti amerò sempre fedelmente. Ogni sacrificio di questa vita sarà ricompensato da una gioia immensa quando solo ti guarderò negli occhi…”.', 'Gossip');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;

-- Dump dei dati della tabella fastpresswriter.comments: ~0 rows (circa)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`commentId`, `authorId`, `articleId`, `commentText`) VALUES
	(1, 1, 1, 'Non ci voleva questa pioggia... Almeno sono partiti rimanendo prudenti');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Dump dei dati della tabella fastpresswriter.users: ~1 rows (circa)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userId`, `name`, `surname`, `password`, `username`, `birthday`, `profileImageURL`, `biography`, `role`) VALUES
	(1, 'Mario', 'Rossi', 'mario', 'User', '1990-04-18', NULL, 'Draculard', 'user'),
	(3, 'Daniele', 'Marceddu', 'dbpp', 'Admin', '1990-02-10', 'https://www.windowsblogitalia.com/wp-content/uploads/2018/08/xbox-elite-white-300x145.jpg', 'grgh', 'author');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
