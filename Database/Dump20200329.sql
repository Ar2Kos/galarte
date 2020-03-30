-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: ip
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `art_piece`
--

DROP TABLE IF EXISTS `art_piece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `art_piece` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(240) DEFAULT NULL,
  `author` int NOT NULL,
  `style` int NOT NULL,
  `date` int DEFAULT NULL,
  `description` text,
  `image` varchar(2083) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_ART_PIECE_APS` (`style`),
  CONSTRAINT `fk_ART_PIECE_APS` FOREIGN KEY (`style`) REFERENCES `style` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `art_piece`
--

LOCK TABLES `art_piece` WRITE;
/*!40000 ALTER TABLE `art_piece` DISABLE KEYS */;
INSERT INTO `art_piece` VALUES (1,'the angelus',1,3,1859,'The painting depicts two peasants bowing in a field over a basket of potatoes to say a prayer, the Angelus, that together with the ringing of the bell from the church on the horizon marks the end of a day s work. Millet was commissioned by the American would-be painter and art collector Thomas Gold Appleton, who never came to collect it. The painting is famous today for driving the prices for artworks of the Barbizon school up to record amounts in the late 19th century.',NULL),(2,'Le Déjeuner sur l herbe',2,3,1863,'Le Déjeuner sur l herbe (The Luncheon on the Grass) originally titled Le Bain (The Bath)  is a large oil on canvas painting by Edouard Manet created in 1862 and 1863. It depicts a female nude and a scantily dressed female bather on a picnic with two fully dressed men in a rural setting. Rejected by the Salon jury of 1863, Manet seized the opportunity to exhibit this and two other paintings in the 1863 Salon des Refusés, where the painting sparked public notoriety and controversy. The piece is now in the Musee d Orsay in Paris. A smaller, earlier version can be seen at the Courtauld Gallery, London.',NULL),(3,'Guernica',3,2,1937,'Guernica is a large 1937 oil painting on canvas by Spanish artist Pablo Picasso. One of Picasso s best known works, Guernica is regarded by many art critics as one of the most moving and powerful anti-war paintings in history, It is exhibited in the Museo Reina Sofia in Madrid.',NULL),(4,'The Weeping Woman',3,2,1937,'The Weeping Woman is an oil on canvas painted by Pablo Picasso in France in 1937. Picasso was intrigued with the subject, and revisited the theme numerous times that year. This painting, created on 26 October 1937, was the most elaborate of the series. Its dimensions are 60 x 49 cm. It has been in the collection of the Tate Modern in London since 1987, and is currently located there.',NULL),(5,'Composition with Yellow, Black, Blue, Red, and Gray',5,1,1921,'Mondrian rotated a square canvas to create a dynamic relationship between the rectilinear composition and the diagonal lines of the edges of the canvas. ',NULL),(6,'Onement 1',4,1,1948,'Newman proclaimed Onement, I to be his artistic breakthrough, giving the work an importance belied by its modest size. This is the first time the artist used a vertical band to define the spatial structure of his work. This band, later dubbed a \"zip,\" became Newman s signature mark. The artist applied the light cadmium red zip atop a strip of masking tape with a palette knife. This thick, irregular band on the smooth field of Indian Red simultaneously divides and unites the composition.',NULL),(7,'The Physical Impossibility of Death in the Mind of Someone Living',6,4,1991,'It is considered the iconic work of British art in the 1990s,[2] and has become a symbol of Britart worldwide.',NULL),(8,'I am not me, the horse is not mine',7,4,2008,'Belongs to a body of work that William Kentridge developed in recent years while producing his version of the opera The Nose ',NULL),(9,'The Death of Socrates',8,5,1787,'In this story, Socrates has been convicted of corrupting the youth of Athens and introducing strange gods, and has been sentenced to die by drinking poison hemlock. Socrates uses his death as a final lesson for his pupils rather than fleeing when the opportunity arises, and faces it calmly.',NULL),(10,'Napoleon I on His Imperial Throne',9,5,1806,'Portrays Napoleon as a Roman god',NULL),(11,'Impression, Sunrise',10,6,1872,'is a painting by Claude Monet first shown at what would become known as the \"Exhibition of the Impressionists\" in Paris in April, 1874. The painting is credited with inspiring the name of the Impressionist movement. ',NULL),(12,'Le Grand Canal',10,6,1908,'t is one of six paintings looking down the Grand Canal towards the Salute church. This Grand Canal series is in turn part of a larger series of paintings of Venice which Monet undertook during 1908 on his only visit to the city. ',NULL),(13,'Dance',11,7,1910,'The composition of dancing figures is commonly recognized as \"a key point of (Matisse s) career and in the development of modern painting\"(Clement).',NULL),(14,'Blue Poles',12,7,1952,'is an abstract expressionist painting by American artist Jackson Pollock. It was purchased amid controversy by the National Gallery of Australia in 1973 and today remains one of the gallery s major paintings. ',NULL),(15,'Wanderer above the Sea of Fog',13,8,1818,'It has been considered one of the masterpieces of Romanticism and one of its most representative works. It currently resides in the Kunsthalle Hamburg in Hamburg, Germany. ',NULL),(16,'Liberty Leading the People',14,8,1830,' A woman of the people with a Phrygian cap personifying the concept of Liberty leads a varied group of people forward over a barricade and the bodies of the fallen, holding the flag of the French Revolution',NULL),(17,'The Persistence of Memory',15,9,1931,'First shown at the Julien Levy Gallery in 1932, since 1934 the painting has been in the collection of the Museum of Modern Art (MoMA) in New York City, which received it from an anonymous donor. It is widely recognized and frequently referenced in popular culture, and sometimes referred to by more descriptive titles, such as \"Melting Clocks\", \"The Soft Watches\" or \"The Melting Watches\"',NULL),(18,'Dream Caused by the Flight of a Bee Around a Pomegranate a Second Before Awakening',15,9,1944,'It is an oil painting on wood. In this \"hand-painted dream photograph\", as Dalí generally called his paintings, there is a seascape of distant horizons and calm waters, perhaps Port Lligat, amidst which Gala is the subject of the scene. Next to the naked body of the sleeping woman, which levitates above a flat rock that floats above the sea, Dalí depicts two suspended droplets of water and a pomegranate, a Christian symbol of fertility and resurrection.',NULL);
/*!40000 ALTER TABLE `art_piece` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `style` int NOT NULL,
  `born` int DEFAULT NULL,
  `death` int DEFAULT NULL,
  `description` text,
  `image` varchar(2083) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_AUTHOR_AS` (`style`),
  CONSTRAINT `fk_AUTHOR_AS` FOREIGN KEY (`style`) REFERENCES `style` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Jean-François Millet',3,1814,1875,' French painter and one of the founders of the Barbizon school in rural France. Millet is noted for his scenes of peasant farmers; he can be categorized as part of the Realism art movement.',NULL),(2,'Édouard Manet',3,1832,1883,'Born into an upper-class household with strong political connections, Manet rejected the future originally envisioned for him, and became engrossed in the world of painting. His early masterworks, The Luncheon on the Grass (Le dejeuner sur l herbe) and Olympia, both 1863, caused great controversy and served as rallying points for the young painters who would create Impressionism. Today, these are considered watershed paintings that mark the start of modern art. The last 20 years of Manet s life saw him form bonds with other great artists of the time, and develop his own style that would be heralded as innovative and serve as a major influence for future painters.',NULL),(3,'Pablo Picasso',2,1881,1973,'Pablo Ruiz Picasso was a Spanish painter, sculptor, printmaker, ceramicist, stage designer, poet and playwright who spent most of his adult life in France. Regarded as one of the most influential artists of the 20th century, he is known for co-founding the Cubist movement, the invention of constructed sculpture,the co-invention of collage, and for the wide variety of styles that he helped develop and explore. Among his most famous works are the proto-Cubist Les Demoiselles d Avignon (1907), and Guernica (1937), a dramatic portrayal of the bombing of Guernica by the German and Italian airforces during the Spanish Civil War.',NULL),(4,'Barnett Newman',1,1905,1970,'He was an American artist. He is seen as one of the major figures in abstract expressionism and one of the foremost of the color field painters. His paintings are existential in tone and content, explicitly composed with the intention of communicating a sense of locality, presence, and contingency.',NULL),(5,'Piet Mondrian',1,1872,1944,'Was a Dutch painter and theoretician who is regarded as one of the greatest artists of the 20th century. He is known for being one of the pioneers of 20th-century abstract art, as he changed his artistic direction from figurative painting to an increasingly abstract style, until he reached a point where his artistic vocabulary was reduced to simple geometric elements.',NULL),(6,'Damien Hirst',4,1965,0,'Is an English artist, entrepreneur, and art collector. He is one of the Young British Artists (YBAs), who dominated the art scene in the UK during the 1990s.',NULL),(7,'William Kentridge',4,1655,0,'Is a South African artist best known for his prints, drawings, and animated films. These are constructed by filming a drawing, making erasures and changes, and filming it again. He continues this process meticulously, giving each change to the drawing a quarter of a second to two seconds  screen time. A single drawing will be altered and filmed this way until the end of a scene. These palimpsest-like drawings are later displayed along with the films as finished pieces of art.',NULL),(8,'Jacques-Louis David',5,1748,1825,'Was a French painter in the Neoclassical style, considered to be the preeminent painter of the era. In the 1780s his cerebral brand of history painting marked a change in taste away from Rococo frivolity toward classical austerity and severity and heightened feeling, harmonizing with the moral climate of the final years of the Ancien Régime. ',NULL),(9,'Jean-Auguste-Dominique Ingres',5,1780,1867,'His expressive distortions of form and space made him an important precursor of modern art, influencing Picasso, Matisse and other modernists. ',NULL),(10,'Claude Monet',6,1840,1926,'French painter, a founder of French Impressionist painting and the most consistent and prolific practitioner of the movement s philosophy of expressing one s perceptions before nature, especially as applied to plein air landscape painting',NULL),(11,'Henri Matisse',7,1869,1954,'Matisse is commonly regarded, along with Pablo Picasso, as one of the artists who best helped to define the revolutionary developments in the visual arts throughout the opening decades of the twentieth century, responsible for significant developments in painting and sculpture.',NULL),(12,'Jackson Pollock',7,1912,1956,'He was widely noticed for his technique of pouring or splashing liquid household paint onto a horizontal surface (drip technique), enabling him to view and paint his canvases from all angles. ',NULL),(13,'Caspar David Friedrich',8,1774,1840,' Was a 19th-century German Romantic landscape painter, generally considered the most important German artist of his generation.',NULL),(14,'Eugène Delacroix',8,1798,1863,'Was a French Romantic artist regarded from the outset of his career as the leader of the French Romantic school.',NULL),(15,'Salvador Dalí',9,1904,1989,'Dalí was a skilled draftsman, best known for the striking and bizarre images in his work.',NULL);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `PLACE_ID` int NOT NULL,
  `AUTHOR_ID` int NOT NULL,
  `Piece` int NOT NULL,
  PRIMARY KEY (`PLACE_ID`,`AUTHOR_ID`,`Piece`),
  KEY `fk_LOCATION_Piece` (`Piece`),
  KEY `fk_LOCATION_AID` (`AUTHOR_ID`),
  CONSTRAINT `fk_LOCATION_AID` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `author` (`ID`),
  CONSTRAINT `fk_LOCATION_PID` FOREIGN KEY (`PLACE_ID`) REFERENCES `place` (`ID`),
  CONSTRAINT `fk_LOCATION_Piece` FOREIGN KEY (`Piece`) REFERENCES `art_piece` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (10,1,1),(10,2,2),(10,3,3),(10,3,4),(10,5,5),(10,4,6),(10,6,7),(10,7,8),(10,8,9),(10,9,10),(10,10,11),(10,10,12),(10,11,13),(10,12,14),(10,13,15),(10,14,16),(10,15,17),(10,15,18);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `PLACE_ID` int NOT NULL,
  `email` varchar(140) NOT NULL,
  `country` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `price` int DEFAULT NULL,
  `country` varchar(35) NOT NULL,
  `city` varchar(35) DEFAULT NULL,
  `latitude` int DEFAULT NULL,
  `longitude` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'Beaux Arts',0,'United-Kingdom','Bath',NULL,NULL),(2,'The Bath Gallery',5,'United-Kingdom','Bath',NULL,NULL),(3,'The Louvre',15,'France','Paris',NULL,NULL),(4,'Victoria Art Gallery',0,'United-Kingdom','Bath',NULL,NULL),(5,'The Holburne Museum',7,'United-Kingdom','Bath',NULL,NULL),(6,'44AD artspace',5,'United-Kingdom','Bath',NULL,NULL),(7,'Gallery Nine',0,'United-Kingdom','Bath',NULL,NULL),(8,'Rostra Gallery',5,'United-Kingdom','Bath',NULL,NULL),(9,'Castle Fine Art',6,'United-Kingdom','Bath',NULL,NULL),(10,'Whitewall Galleries',0,'United-Kingdom','Bath',NULL,NULL);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `style`
--

DROP TABLE IF EXISTS `style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `style` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `era` int NOT NULL,
  `Description` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `style`
--

LOCK TABLES `style` WRITE;
/*!40000 ALTER TABLE `style` DISABLE KEYS */;
INSERT INTO `style` VALUES (1,'Abstract',20,'Strictly speaking, the word abstract means to separate or withdraw something from something else. The term can be applied to art that is based an object, figure or landscape, where forms have been simplified or schematised. It is also applied to art that uses forms, such as geometric shapes or gestural marks, which have no source at all in an external visual reality. Some artists of this ‘pure’ abstraction have preferred terms such as concrete art or non-objective art, but in practice the word abstract is used across the board and the distinction between the two is not always obvious.'),(2,'Cubism',20,'The movement was pioneered by Pablo Picasso and Georges Braque, joined by Jean Metzinger, Albert Gleizes, Robert Delaunay, Henri Le Fauconnier, and Fernand Léger. One primary influence that led to Cubism was the representation of three-dimensional form in the late works of Paul Cézanne. A retrospective of Cézanne s paintings had been held at the Salon d Automne of 1904, current works were displayed at the 1905 and 1906 Salon d Automne, followed by two commemorative retrospectives after his death in 1907. In Cubist artwork, objects are analyzed, broken up and reassembled in an abstracted form—instead of depicting objects from a single viewpoint, the artist depicts the subject from a multitude of viewpoints to represent the subject in a greater context.'),(3,'Realism',19,'This movement was a reaction against romanticism. Romanticism was an earlier movement that presented the world in much more idealized terms. '),(4,'Contemporary',21,'Contemporary art is the art of today, produced in the second half of the 20th century or in the 21st century. Contemporary artists work in a globally influenced, culturally diverse, and technologically advancing world. Their art is a dynamic combination of materials, methods, concepts, and subjects that continue the challenging of boundaries that was already well underway in the 20th century. Diverse and eclectic, contemporary art as a whole is distinguished by the very lack of a uniform, organising principle, ideology, or \"-ism\". Contemporary art is part of a cultural dialogue that concerns larger contextual frameworks such as personal and cultural identity, family, community, and nationality. '),(5,'Neoclassicism',19,' Neoclassicism was born in Rome largely thanks to the writings of Johann Joachim Winckelmann, at the time of the rediscovery of Pompeii and Herculaneum, but its popularity spread all over Europe as a generation of European art students finished their Grand Tour and returned from Italy to their home countries with newly rediscovered Greco-Roman ideals. The main Neoclassical movement coincided with the 18th-century Age of Enlightenment, and continued into the early 19th century, laterally competing with Romanticism. In architecture, the style continued throughout the 19th, 20th and up to the 21st century. '),(6,'Impressionism',19,'Is an art movement characterized by relatively small, thin, yet visible brush strokes, open composition, emphasis on accurate depiction of light in its changing qualities (often accentuating the effects of the passage of time), ordinary subject matter, inclusion of movement as a crucial element of human perception and experience, and unusual visual angles. Impressionism originated with a group of Paris-based artists whose independent exhibitions brought them to prominence during the 1870s and 1880s. '),(7,'Modernism',20,'Among the factors that shaped modernism were the development of modern industrial societies and the rapid growth of cities, followed then by reactions to the horrors of World War I. Modernism also rejected the certainty of Enlightenment thinking, although many modernists also rejected religious belief. A notable characteristic of modernism is self-consciousness and irony concerning literary and social traditions, which often led to experiments with form, along with the use of techniques that drew attention to the processes and materials used in creating a painting, poem, building and other works of art.'),(8,'Romanticism',19,'The movement emphasized intense emotion as an authentic source of aesthetic experience, placing new emphasis on such emotions as apprehension, horror and terror, and awe—especially that experienced in confronting the new aesthetic categories of the sublimity and beauty of nature. It elevated folk art and ancient custom to something noble, but also spontaneity as a desirable characteristic (as in the musical impromptu). In contrast to the Rationalism and Classicism of the Enlightenment, Romanticism revived medievalism[7] and elements of art and narrative perceived as authentically medieval in an attempt to escape population growth, early urban sprawl, and industrialism. '),(9,'Surrealism',20,'Is best known for its visual artworks and writings. Artists painted unnerving, illogical scenes, sometimes with photographic precision, creating strange creatures from everyday objects, and developing painting techniques that allowed the unconscious to express itself.');
/*!40000 ALTER TABLE `style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_pref`
--

DROP TABLE IF EXISTS `user_pref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_pref` (
  `ID` int NOT NULL,
  `Style` int NOT NULL,
  `liked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Style`),
  KEY `fk_USER_PREF_USS` (`Style`),
  CONSTRAINT `fk_USER_PREF_UID` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `fk_USER_PREF_USS` FOREIGN KEY (`Style`) REFERENCES `style` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_pref`
--

LOCK TABLES `user_pref` WRITE;
/*!40000 ALTER TABLE `user_pref` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `email` varchar(140) NOT NULL,
  `country` varchar(35) NOT NULL,
  `city` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-29 12:45:32
