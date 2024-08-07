USE [master]
GO


-- Drop the database if it already exists
IF EXISTS (SELECT name
FROM sys.databases
WHERE name = N'SWP391_SU24')
BEGIN
    DROP DATABASE [SWP391_SU24];
END
GO


/****** Object:  Database [SWP391_SU24]    Script Date: 6/21/2024 8:46:29 PM ******/
CREATE DATABASE [SWP391_SU24]
CONTAINMENT = NONE
GO

ALTER DATABASE [SWP391_SU24] SET COMPATIBILITY_LEVEL = 150
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
BEGIN
    EXEC [SWP391_SU24].[dbo].[sp_fulltext_database] @action = 'enable'
END
GO


ALTER DATABASE [SWP391_SU24] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SWP391_SU24] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SWP391_SU24] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SWP391_SU24] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SWP391_SU24] SET ARITHABORT OFF 
GO
ALTER DATABASE [SWP391_SU24] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [SWP391_SU24] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SWP391_SU24] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SWP391_SU24] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SWP391_SU24] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SWP391_SU24] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SWP391_SU24] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SWP391_SU24] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SWP391_SU24] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SWP391_SU24] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SWP391_SU24] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SWP391_SU24] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SWP391_SU24] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SWP391_SU24] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SWP391_SU24] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SWP391_SU24] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SWP391_SU24] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SWP391_SU24] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SWP391_SU24] SET  MULTI_USER 
GO
ALTER DATABASE [SWP391_SU24] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SWP391_SU24] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SWP391_SU24] SET FILESTREAM (NON_TRANSACTED_ACCESS = OFF) 
GO
ALTER DATABASE [SWP391_SU24] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SWP391_SU24] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SWP391_SU24] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SWP391_SU24] SET QUERY_STORE = OFF
GO


USE [SWP391_SU24]
GO


/****** Object:  Table [dbo].[Anime]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Anime]
(
    [AnimeID] [int] IDENTITY(1,1) NOT NULL,
    [Title] [nvarchar](100) NOT NULL,
    [Synopsis] [text] NULL,
    [GenreId] [int] NULL,
    [Episodes] [int] NULL,
    [Status] [varchar](20) NULL,
    [ReleaseDate] [datetime] NULL,
    [Studio] [nvarchar](100) NULL,
    [imageAnime] [varchar](100) NULL,

    PRIMARY KEY CLUSTERED ([AnimeID] ASC)
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Category]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category]
(
    [CategoryID] [int] IDENTITY(1,1) NOT NULL,
    [Name] [nvarchar](50) NOT NULL,
    [Description] [text] NULL,

    PRIMARY KEY CLUSTERED ([CategoryID] ASC)
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Comment]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment]
(
    [CommentID] [int] IDENTITY(1,1) NOT NULL,
    [PostID] [int] NULL,
    [UserID] [int] NULL,
    [Content] [text] NULL,
    [Status] [varchar](20) NULL,
    [Vote] [int] NOT NULL,
    [ParentId] [int] NULL,
    [CreateAt] [datetime] NOT NULL,

    PRIMARY KEY CLUSTERED ([CommentID] ASC)
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Genre]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Genre]
(
    [GenreID] [int] IDENTITY(1,1) NOT NULL,
    [Name] [nvarchar](50) NOT NULL,
    [Description] [text] NULL,

    PRIMARY KEY CLUSTERED ([GenreID] ASC)
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


/****** Object:  Table [dbo].[GenreAnime]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GenreAnime]
(
    [AnimeID] [int] NOT NULL,
    [GenreID] [int] NOT NULL,

    PRIMARY KEY CLUSTERED ([AnimeID] ASC, [GenreID] ASC)
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Media]    Script Date: 6/21/2024 8:46:29 PM ******/
--SET ANSI_NULLS ON
--GO
--SET QUOTED_IDENTIFIER ON
--GO
--CREATE TABLE [dbo].[Media]
--(
--    [MediaID] [int] IDENTITY(1,1) NOT NULL,
--    [UserID] [int] NULL,
--    [FileName] [varchar](100) NULL,
--    [Path] [text] NULL,
--    [Type] [varchar](20) NULL,
--    [Uploaded] [varchar](50) NOT NULL,

--    PRIMARY KEY CLUSTERED ([MediaID] ASC)
--	WITH (
--        PAD_INDEX = OFF, 
--        STATISTICS_NORECOMPUTE = OFF, 
--        IGNORE_DUP_KEY = OFF, 
--        ALLOW_ROW_LOCKS = ON, 
--        ALLOW_PAGE_LOCKS = ON, 
--        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
--    ) ON [PRIMARY]
--) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
--GO


/****** Object:  Table [dbo].[Post]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post]
(
    [PostID] [int] IDENTITY(1,1) NOT NULL,
    [UserID] [int] NULL,
    [CategoryID] [int] NULL,
    [Title] [text] NULL,
    [Content] [text] NULL,
    [Source] [nvarchar](100) NULL,
    [Image] [varchar](100) NULL,
    [PostTime] [datetime] NOT NULL,
    [Status] [varchar](20) NULL,
    [Vote] [int] NOT NULL,
    [View] [int] NOT NULL,

    PRIMARY KEY CLUSTERED ([PostID] ASC)
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


/****** Object:  Table [dbo].[PostGenre]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PostGenre]
(
    [PostID] [int] Not NULL,
    [GenreID] [int] Not NULL,

    PRIMARY KEY CLUSTERED ([PostID] ASC, [GenreID] ASC)
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Rank]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rank]
(
    [RankID] [int] IDENTITY(1,1) NOT NULL,
    [Name] [nvarchar](50) NOT NULL,
    [PointsRequired] [int] NULL,
    [Color] [varchar](20) NULL,

    PRIMARY KEY CLUSTERED ([RankID] ASC)
    WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Report]    Script Date: 6/21/2024 8:46:29 PM ******/
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Report]
(
    [ReportID] [int] IDENTITY(1, 1) NOT NULL,
    [UserID] [int] NOT NULL,
    [TimeCreated] [datetime] NOT NULL,
    [Reason] [text] NOT NULL,
    [PostID] [int] NOT NULL,
    [Status] [varchar](8) NOT NULL,
    [Note] [text] NULL,

    PRIMARY KEY CLUSTERED ([ReportID] ASC) 
    WITH (
        PAD_INDEX = OFF,
        STATISTICS_NORECOMPUTE = OFF,
        IGNORE_DUP_KEY = OFF,
        ALLOW_ROW_LOCKS = ON,
        ALLOW_PAGE_LOCKS = ON,
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY];
GO


/****** Object:  Table [dbo].[Report]    Script Date: 7/2/2024 12:47:29 PM ******/
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ticket]
(
    [TicketID] [int] IDENTITY(1, 1) NOT NULL,
    [UserID] [int] NOT NULL,
    [TimeCreated] [datetime] NOT NULL,
    [Content] [text] NOT NULL,
    [Status] [varchar](8) NOT NULL,
    [Note] [text] NULL,

    PRIMARY KEY CLUSTERED ([TicketID] ASC) 
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Ban]    Script Date: 7/2/2024 12:47:29 PM ******/
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ban]
(
    [BanId] [int] IDENTITY(1,1) NOT NULL,
    [BannedUserId] [int] NOT NULL,
    [BanSourceId] [int],
    [BanReason] [nvarchar](255) NOT NULL,
    [BannedDate] [datetime] NOT NULL,
    [ExpireDate] [datetime] NOT NULL,
    [SourcePostId] [int] foreign key references post(postid) NULL,
    PRIMARY KEY CLUSTERED ([BanId] ASC) 
	WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Role]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role]
(
    [RoleID] [int] IDENTITY(1,1) NOT NULL,
    [RoleName] [varchar](50) NOT NULL,

    PRIMARY KEY CLUSTERED ([RoleID] ASC) 
    WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[User]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User]
(
    [UserID] [int] IDENTITY(1,1) NOT NULL,
    [Username] [varchar](50) NOT NULL,
    [Password] [varchar](100) NOT NULL,
    [RoleID] [int] NULL,
    [Point] [int] NULL,
    [Status] [varchar](20) NULL,
    [Email] [varchar](100) NULL,
    [Fullname] [nvarchar](100) NULL,
    [RankID] [int] NULL,
    [ProfilePhoto] [varchar](255) NULL,
    [PhoneNumber] [varchar](20) NULL,
    [DateOfBirth] [datetime] NULL,
    [CreationDate] [datetime] NOT NULL,
    [Bio] [nvarchar](255) NULL,

    PRIMARY KEY CLUSTERED ([UserID] ASC)
    WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[VoteUserCmt]    Script Date: 6/21/2024 8:46:29 PM ******/
--SET ANSI_NULLS ON
--GO
--SET QUOTED_IDENTIFIER ON
--GO
--CREATE TABLE [dbo].[VoteUserCmt]
--(
--    [UserID] [int] NOT NULL,
--    [CommentID] [int] NOT NULL,
--    [Status] [varchar](20) NULL,

--    PRIMARY KEY CLUSTERED ([UserID] ASC, [CommentID] ASC)
--    WITH (
--        PAD_INDEX = OFF, 
--        STATISTICS_NORECOMPUTE = OFF, 
--        IGNORE_DUP_KEY = OFF, 
--        ALLOW_ROW_LOCKS = ON, 
--        ALLOW_PAGE_LOCKS = ON, 
--        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
--    ) ON [PRIMARY]
--) ON [PRIMARY]
--GO


/****** Object:  Table [dbo].[VoteUserPost]    Script Date: 6/21/2024 8:46:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VoteUserPost]
(
    [UserID] [int] NOT NULL,
    [PostID] [int] NOT NULL,
    [Status] [varchar](20) NULL,

    PRIMARY KEY CLUSTERED ([UserID] ASC, [PostID] ASC)
    WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON, 
        OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
    ) ON [PRIMARY]
) ON [PRIMARY]
GO


SET IDENTITY_INSERT [dbo].[Anime] ON
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        1,
        N'Steins;Gate',
        N'Rintarou Okabe, a self-proclaimed mad scientist, discovers a way to send messages to the past using a microwave. As he and his friends experiment with time travel, they uncover a conspiracy that puts their lives and the fate of humanity at risk.',
        7,
        24,
        N'Completed',
        CAST(N'2011-04-06T00:00:00.000' AS DateTime),
        N'White Fox',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        2,
        N'Cowboy Bebop',
        N'In the year 2071, bounty hunter Spike Spiegel and his crew aboard the spaceship Bebop chase down criminals across the galaxy. As they confront their pasts and face new adversaries, they must navigate a universe filled with danger and intrigue.',
        6,
        26,
        N'Completed',
        CAST(N'1998-04-03T00:00:00.000' AS DateTime),
        N'Sunrise',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        3,
        N'Neon Genesis Evangelion',
        N'In a post-apocalyptic world, humanity battles mysterious beings known as Angels using giant mechs called Evangelions. As teenager Shinji Ikari pilots an Evangelion to protect humanity, he grapples with his own fears and the secrets surrounding the Angels and Evangelions.',
        1,
        26,
        N'Completed',
        CAST(N'1995-10-04T00:00:00.000' AS DateTime),
        N'Gainax',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        4,
        N'Dragon Ball Z',
        N'Goku and his friends defend Earth from powerful villains and explore the universe in search of powerful Dragon Balls. With each new threat, Goku pushes himself beyond his limits to protect his loved ones and uphold justice.',
        1,
        291,
        N'Completed',
        CAST(N'1989-04-26T00:00:00.000' AS DateTime),
        N'Toei Animation',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        5,
        N'One Piece', N'Monkey D. Luffy dreams of becoming the Pirate King by finding the legendary treasure known as One Piece. Along with his crew, the Straw Hat Pirates, Luffy explores the Grand Line, encountering powerful foes and forming lasting friendships on his epic journey.',
        13,
        1000,
        N'Ongoing',
        CAST(N'1999-10-20T00:00:00.000' AS DateTime),
        N'Toei Animation',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        6,
        N'Naruto',
        N'Naruto Uzumaki, a young ninja with dreams of becoming Hokage, navigates the challenges of life in the Hidden Leaf Village. With his determination and spirit, Naruto aims to earn the respect of his peers and protect his loved ones.',
        13,
        220,
        N'Completed',
        CAST(N'2002-10-03T00:00:00.000' AS DateTime),
        N'Studio Pierrot',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        7,
        N'Death Note',
        N'Light Yagami discovers a mysterious notebook that grants him the power to kill anyone whose name he writes in it. Using the Death Note, Light sets out to cleanse the world of criminals, drawing the attention of the enigmatic detective known only as L.',
        7,
        37,
        N'Completed',
        CAST(N'2006-10-03T00:00:00.000' AS DateTime),
        N'Madhouse',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        8,
        N'Spirited Away',
        N'Chihiro Ogino finds herself trapped in a mysterious world of spirits after her parents undergo a bizarre transformation. To save her family and return to the human world, Chihiro must work at a bathhouse and navigate a realm ruled by gods and monsters.',
        11,
        1,
        N'Completed',
        CAST(N'2001-07-20T00:00:00.000' AS DateTime),
        N'Studio Ghibli',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        9,
        N'Your Name',
        N'Mitsuha Miyamizu and Taki Tachibana discover they are mysteriously swapping bodies. As they unravel the truth behind their connection and navigate their unusual predicament, they develop a deep bond transcending time and space.',
        5,
        1,
        N'Completed',
        CAST(N'2016-08-26T00:00:00.000' AS DateTime),
        N'CoMix Wave Films',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        10,
        N'Demon Slayer',
        N'Tanjiro Kamado embarks on a quest to avenge his family and cure his sister, who has been turned into a demon. Alongside the Demon Slayer Corps, Tanjiro battles terrifying demons and uncovers dark secrets about his family''s past.',
        12,
        26,
        N'Completed',
        CAST(N'2019-04-06T00:00:00.000' AS DateTime),
        N'Aniplex',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        11,
        N'Hunter x Hunter',
        N'Gon Freecss, a young boy with dreams of finding his absentee father, becomes a Hunter to pursue his quest. Alongside his friends Kurapika, Leorio, and Killua, Gon faces perilous challenges and discovers the true meaning of strength.',
        13,
        148,
        N'Completed',
        CAST(N'2011-10-02T00:00:00.000' AS DateTime),
        N'Nippon Animation',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        12,
        N'Sword Art Online',
        N'In the near future, players immerse themselves in the virtual reality game Sword Art Online. When they discover they are unable to log out, they must clear the game''s deadly challenges to survive. Kirito, a skilled player, teams up with Asuna to conquer the game and uncover its mysteries.',
        15,
        75,
        N'Completed',
        CAST(N'2012-07-07T00:00:00.000' AS DateTime),
        N'A-1 Pictures',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        13,
        N'My Hero Academia',
        N'In a world where people with superpowers, known as "Quirks," are common, Izuku Midoriya dreams of becoming a Hero despite being Quirkless. With the help of his mentor All Might, Izuku enrolls in U.A. High School to hone his abilities and protect humanity.',
        1,
        113,
        N'Ongoing',
        CAST(N'2016-04-03T00:00:00.000' AS DateTime),
        N'Bones',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        14,
        N'Fullmetal Alchemist: Brotherhood',
        N'Edward and Alphonse Elric seek the Philosopher''s Stone to restore their bodies after a failed alchemical experiment. Their journey leads them to uncover dark truths about alchemy and the government, testing their resolve and morality.',
        2,
        64,
        N'Completed',
        CAST(N'2009-04-05T00:00:00.000' AS DateTime),
        N'Bones',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        15,
        N'Attack on Titan',
        N'Several hundred years ago, humanity was on the brink of extinction due to the Titans, gigantic humanoid creatures. To protect themselves, humans built massive walls and lived peacefully for a century. However, everything changes when a colossal Titan breaches the wall, unleashing chaos and forcing Eren Yeager, Mikasa Ackerman, and Armin Arlert into a desperate battle for survival.',
        1,
        75,
        N'Completed',
        CAST(N'2013-04-07T00:00:00.000' AS DateTime),
        N'Wit Studio',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        16,
        N'Naruto Shippuden',
        N'Naruto Uzumaki continues his journey to become Hokage and protect his village. As he faces new enemies and challenges, Naruto''s resolve and bonds with his friends are tested.',
        13,
        500,
        N'Completed',
        CAST(N'2007-02-15T00:00:00.000' AS DateTime),
        N'Studio Pierrot',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        17,
        N'Tokyo Ghoul:re',
        N'In the sequel to Tokyo Ghoul, Haise Sasaki leads the CCG''s Quinx Squad, composed of humans with ghoul-like abilities. As conflicts between ghouls and humans escalate, Haise struggles with his identity and loyalties.',
        4,
        12,
        N'Completed',
        CAST(N'2018-04-03T00:00:00.000' AS DateTime),
        N'Studio Pierrot',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        18,
        N'Attack on Titan: The Final Season Part 2', N'Eren Yeager and his allies face the last remnants of Marley''s military forces as they struggle for freedom and survival. The truth behind the Titans and their world-shaking origins is finally revealed.',
        1,
        12,
        N'Ongoing',
        CAST(N'2023-01-09T00:00:00.000' AS DateTime),
        N'MAPPA',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        19,
        N'My Hero Academia: World Heroes'' Mission',
        N'Class 1-A heroes embark on a global mission to save the world from a terrorist organization targeting Quirk users. Deku, Bakugo, and Todoroki face their toughest challenge yet as they fight to protect humanity.',
        1,
        1,
        N'Released',
        CAST(N'2021-08-06T00:00:00.000' AS DateTime),
        N'Bones',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        20,
        N'Jujutsu Kaisen',
        N'Yuji Itadori, a high school student with impressive athletic abilities, joins a secret group of sorcerers to exterminate cursed spirits. He harnesses the power of a powerful curse to protect the innocent and uncover the truth about curses.',
        2,
        24,
        N'Ongoing',
        CAST(N'2020-10-03T00:00:00.000' AS DateTime),
        N'MAPPA',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        21,
        N'Demon Slayer: Kimetsu no Yaiba - Mugen Train', N'Tanjiro and his friends embark on a perilous mission aboard the Mugen Train to investigate a series of disappearances. They encounter the powerful demon Enmu, testing their skills and resolve to protect humanity.',
        12,
        1,
        N'Released',
        CAST(N'2020-10-16T00:00:00.000' AS DateTime),
        N'ufotable',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        22,
        N'Dragon Ball Super',
        N'Goku and his friends face new powerful foes from other universes as they compete in the Tournament of Power. With the fate of multiple universes at stake, Goku strives to achieve new levels of power to protect his friends and family.',
        1,
        131,
        N'Completed',
        CAST(N'2015-07-05T00:00:00.000' AS DateTime),
        N'Toei Animation',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        23,
        N'One Punch Man 2nd Season',
        N'Saitama continues his quest for a worthy opponent while facing new threats to humanity. As heroes and monsters clash, Saitama''s overwhelming strength remains unmatched, leaving him bored with the lack of challenge.',
        6,
        12,
        N'Completed',
        CAST(N'2019-04-10T00:00:00.000' AS DateTime),
        N'J.C. Staff',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        24,
        N'Hunter x Hunter: Chimera Ant Arc', N'Gon and Killua venture into the dangerous Chimera Ant territory to rescue their friend Kite and confront the Chimera Ant King. As they face the strongest foes yet, Gon''s determination reaches new heights.',
        13,
        61,
        N'Completed',
        CAST(N'2013-04-21T00:00:00.000' AS DateTime),
        N'Madhouse',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        25,
        N'Fate/Zero', N'Mages and heroic spirits compete in the Fourth Holy Grail War for the wish-granting Holy Grail. As alliances are forged and betrayals unfold, the true nature of humanity''s desires and sacrifices are revealed.',
        3,
        25,
        N'Completed',
        CAST(N'2011-10-02T00:00:00.000' AS DateTime),
        N'ufotable',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        26,
        N'Bleach: Thousand-Year Blood War Arc', N'Ichigo Kurosaki returns to protect the Soul Society from the Quincy invaders led by Yhwach. With new allies and powerful enemies, Ichigo''s resolve to protect his loved ones is tested in the final battle for the fate of the world.',
        5,
        366,
        N'Ongoing',
        CAST(N'2022-10-02T00:00:00.000' AS DateTime),
        N'Studio Pierrot',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        27,
        N'Steins;Gate 0',
        N'Okabe Rintarou struggles to cope with the aftermath of failed time travel experiments. Amidst a looming future of dystopia, Okabe seeks a way to save his friends and prevent world-changing events from unfolding.',
        7,
        23,
        N'Completed',
        CAST(N'2018-04-12T00:00:00.000' AS DateTime),
        N'White Fox',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        28,
        N'Black Clover',
        N'Asta continues his journey to become the Wizard King, challenging powerful foes and expanding his magic abilities. With the Clover Kingdom facing new threats, Asta and his friends defend their home and forge alliances.',
        10,
        170,
        N'Completed',
        CAST(N'2017-10-03T00:00:00.000' AS DateTime),
        N'Studio Pierrot',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        29,
        N'Attack on Titan: Lost Girls',
        N'Annie Leonhart and Mikasa Ackerman face personal trials and uncover hidden truths in the shadows of humanity''s struggle against Titans. Their choices shape their destinies as they navigate a world filled with secrets.',
        1,
        3,
        N'Completed',
        CAST(N'2018-12-08T00:00:00.000' AS DateTime),
        N'Wit Studio',
        NULL
    )
INSERT [dbo].[Anime]
    ([AnimeID], [Title], [Synopsis], [GenreId], [Episodes], [Status], [ReleaseDate], [Studio], [imageAnime])
VALUES
    (
        30,
        N'Naruto: The Last',
        N'As the moon threatens to collide with Earth, Naruto Uzumaki embarks on a mission to rescue Hanabi Hyuga and save the world. Alongside Hinata Hyuga, Naruto confronts his feelings and fights for a future where love triumphs over darkness.',
        13,
        1,
        N'Completed',
        CAST(N'2014-12-06T00:00:00.000' AS DateTime),
        N'Studio Pierrot',
        NULL
    )
SET IDENTITY_INSERT [dbo].[Anime] OFF
GO


SET IDENTITY_INSERT [dbo].[Category] ON
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        1,
        N'Anime Reviews ',
        N'Reviews and evaluations of anime series, covering aspects such as plot, animation, characters, music, and screenplay.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        2,
        N'Character Analysis',
        N'Focuses on analyzing the personalities, actions, and roles of characters within anime.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        3,
        N'Plot Analysis ',
        N'Analyzes the storyline of anime, including its structure, development trends, and key details.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        4,
        N'Anime Comparisons',
        N'Compares different anime series or compares anime adaptations with their source material, analyzing differences and assessing success.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        5,
        N'Genre Analysis',
        N'Studies and analyzes various anime genres such as action, comedy, horror, science fiction, and the characteristic elements of each genre.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        6,
        N'History and Evolution',
        N'Discusses the history and development of the anime industry, including key periods and cultural influences.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        7,
        N'Top Lists ',
        N'Compiles top lists of anime series based on criteria such as ratings, genres, characters, or special categories like beginner-friendly anime.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        8,
        N'Interviews',
        N'Features interviews with anime creators, filmmakers, voice actors, or industry experts.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        9,
        N'Art Analysis',
        N'Analyzes and comments on the artistry in anime, including animation, color schemes, character design, and backgrounds.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        10,
        N'Question ',
        N'Articles that pose questions about various aspects of anime, encouraging discussion and exploration of different topics within the anime community.'
    )
INSERT [dbo].[Category]
    ([CategoryID], [Name], [Description])
VALUES
    (
        11,
        N'Community Articles ',
        N'Discusses the anime fan community, events, anime-related activities, and the impact of anime on the community.'
    )
SET IDENTITY_INSERT [dbo].[Category] OFF
GO


SET IDENTITY_INSERT [dbo].[Comment] ON
INSERT [dbo].[Comment]
    ([CommentID], [PostID], [UserID], [Content], [Status], [Vote], [ParentId], [CreateAt])
VALUES
    (
        1,
        54,
        1,
        N'N?i dung comment c?a b?n',
        N'active',
        0,
        NULL,
        CAST(N'2024-06-20T00:47:05.207' AS DateTime)
    )
INSERT [dbo].[Comment]
    ([CommentID], [PostID], [UserID], [Content], [Status], [Vote], [ParentId], [CreateAt])
VALUES
    (
        2,
        54,
        1,
        N'N?i dung comment c?a b?n2',
        N'active',
        0,
        NULL,
        CAST(N'2024-06-20T00:47:31.320' AS DateTime)
    )
SET IDENTITY_INSERT [dbo].[Comment] OFF
GO


SET IDENTITY_INSERT [dbo].[Genre] ON
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        1,
        N'Mecha',
        N'Focuses on the use of giant robots or mechanical suits, often piloted by characters in battles against enemies or monsters.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        2,
        N'Slice of Life',
        N'Captures the everyday experiences and moments of characters, portraying their ordinary lives and personal growth.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        3,
        N'Fantasy',
        N'Incorporates elements of magic, mythical creatures, and fantastical worlds, featuring epic adventures and legendary battles.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        4,
        N'Horror',
        N'Creates a chilling atmosphere of fear, suspense, and dread, often involving supernatural entities like ghosts and monsters.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        5,
        N'Romance',
        N'Explores the intricacies and developments of romantic relationships between central characters, ranging from initial encounters to deep emotional bonds.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        6,
        N'Comedy',
        N'Aims to entertain through humor, using funny situations, witty dialogues, and quirky characters.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        7,
        N'Mystery',
        N'Involves solving intricate puzzles and unraveling secrets, often set against a backdrop of crime or supernatural phenomena.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        8,
        N'Supernatural',
        N'Explores phenomena beyond the natural world, such as ghosts, spirits, and paranormal activities, blending elements of horror, fantasy, and mystery.'
                                                      )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        9,
        N'Psychological',
        N'Delves into the complex workings of the human mind and behavior, exploring deep themes related to mental health and existential questions.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        10,
        N'Thriller',
        N'Keeps the audience on the edge of their seats with intense, suspenseful storylines and fast-paced action.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        11,
        N'Historical',
        N'Set against the backdrop of real historical periods, often featuring significant events and figures intertwined with fictional narratives.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        12,
        N'Sports',
        N'Focuses on competitive sports and athletic pursuits, highlighting the challenges and triumphs of athletes striving for greatness.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        13,
        N'Adventure',
        N'Follows characters on epic journeys and quests, exploring fantastical realms or real-world settings filled with discovery and exploration.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        14,
        N'Music',
        N'Centers around the world of music, showcasing characters involved in the music industry, bands, and the creation of musical masterpieces.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        15,
        N'Sci-Fi',
        N'Explores futuristic concepts, advanced technology, space exploration, and scientific advancements, often portraying alternative realities or speculative futures.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        16,
        N'Ecchi',
        N'Uses sexual humor and risqué situations for comedic effect, rather than explicit content.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        17,
        N'Harem',
        N'Features a protagonist surrounded by multiple characters of the opposite sex who are romantically interested in them, creating romantic and comedic entanglements.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        18,
        N'Yaoi',
        N'Focuses on romantic relationships between male characters, typically appealing to a female audience.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        19,
        N'Yuri',
        N'Explores romantic relationships between female characters, appealing to both male and female viewers with its emotional and intimate narratives.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        20,
        N'Detective',
        N'Focuses on solving mysteries and crimes, often involving detectives, forensic investigations, and psychological suspense.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        21,
        N'Fantasy',
        N'Involves imaginative and speculative elements, exploring unreal or fantastical concepts beyond the realms of normal reality.'
    )
INSERT [dbo].[Genre]
    ([GenreID], [Name], [Description])
VALUES
    (
        22,
        N'Isekai',
        N'Features characters who are transported to and find themselves in another world, often a fantasy or game-like environment, where they embark on new adventures and challenges.'
    )
SET IDENTITY_INSERT [dbo].[Genre] OFF
GO


SET IDENTITY_INSERT [dbo].[Post] ON
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        1,
        1,
        1,
        N'Review: Attack on Titan Season 4',
        NULL,
        N'AnimeReviewCentral',
        N'attack-on-titan-season4.jpg',
        CAST(N'2024-06-10T08:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        2,
        2,
        1,
        N'Review: Demon Slayer - Kimetsu no Yaiba',
        NULL,
        N'AnimeMagazine',
        N'demon-slayer-kimetsu-no-yaiba.jpg',
        CAST(N'2024-06-11T09:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        3,
        3,
        1,
        N'Review: My Hero Academia Season 5',
        NULL,
        N'OtakuNews',
        N'my-hero-academia-season5.jpg',
        CAST(N'2024-06-12T10:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        4,
        4,
        1,
        N'Review: One Punch Man Season 2',
        NULL,
        N'AnimeInsider',
        N'one-punch-man-season2.jpg',
        CAST(N'2024-06-13T11:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        5,
        5,
        1,
        N'Review: Sword Art Online Alicization',
        NULL,
        N'AnimeWorld',
        N'sword-art-online-alicization.jpg',
        CAST(N'2024-06-14T12:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        6,
        1,
        2,
        N'Character Analysis: Naruto Uzumaki',
        NULL, N'AnimeCharacterAnalysis', N'
        naruto-uzumaki.jpg',
        CAST(N'2024-06-15T13:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        7,
        2,
        2,
        N'Character Analysis: Mikasa Ackerman',
        NULL,
        N'AnimeInsights',
        N'mikasa-ackerman.jpg',
        CAST(N'2024-06-16T14:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        8,
        3,
        2,
        N'Character Analysis: Luffy from One Piece',
        NULL,
        N'AnimeFanatics',
        N'luffy-one-piece.jpg',
        CAST(N'2024-06-17T15:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        9,
        4,
        2,
        N'Character Analysis: Goku from Dragon Ball Z',
        NULL,
        N'AnimeUniverse',
        N'goku-dragon-ball-z.jpg',
        CAST(N'2024-06-18T16:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        10,
        5,
        2,
        N'Character Analysis: Asuka Langley Soryu',
        NULL,
        N'AnimeEva',
        N'asuka-langley-soryu.jpg',
        CAST(N'2024-06-19T17:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        11,
        1,
        3,
        N'Plot Analysis: Death Note',
        NULL,
        N'AnimeCritics',
        N'death-note-plot-analysis.jpg',
        CAST(N'2024-06-20T18:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        12,
        2,
        3,
        N'Plot Analysis: Attack on Titan',
        NULL,
        N'AnimeFandom',
        N'attack-on-titan-plot-analysis.jpg',
        CAST(N'2024-06-21T19:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        13,
        3,
        3,
        N'Plot Analysis: Fullmetal Alchemist',
        NULL,
        N'AnimeJournals',
        N'fullmetal-alchemist-plot-analysis.jpg',
        CAST(N'2024-06-22T20:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        14,
        4,
        3,
        N'Plot Analysis: Neon Genesis Evangelion',
        NULL,
        N'AnimeGenesis',
        N'neon-genesis-evangelion-plot.jpg',
        CAST(N'2024-06-23T21:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        15,
        5,
        3,
        N'Plot Analysis: Steins;Gate',
        NULL,
        N'AnimeGate',
        N'steins-gate-plot-analysis.jpg',
        CAST(N'2024-06-24T22:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        16,
        1,
        4,
        N'Comparison: Naruto vs. One Piece',
        NULL,
        N'AnimeDebate',
        N'naruto-vs-one-piece.jpg',
        CAST(N'2024-06-25T23:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        17,
        2,
        4,
        N'Comparison: My Hero Academia vs. Black Clover',
        NULL,
        N'AnimeVersus',
        N'my-hero-academia-vs-black-clover.jpg',
        CAST(N'2024-06-26T00:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        18,
        3,
        4,
        N'Comparison: Attack on Titan vs. Tokyo Ghoul',
        NULL,
        N'AnimeShowdown',
        N'attack-on-titan-vs-tokyo-ghoul.jpg',
        CAST(N'2024-06-27T01:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        19,
        4,
        4,
        N'Comparison: Hunter x Hunter vs. Bleach',
        NULL,
        N'AnimeMatchup',
        N'hunter-x-hunter-vs-bleach.jpg',
        CAST(N'2024-06-28T02:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        20,
        5,
        4,
        N'Comparison: Sword Art Online vs. Log Horizon',
        NULL,
        N'AnimeWorldsCollide',
        N'sword-art-online-vs-log-horizon.jpg',
        CAST(N'2024-06-29T03:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        21,
        1,
        5,
        N'Genre Analysis: Shonen Anime',
        NULL,
        N'AnimeGenres',
        N'shonen-anime-genre.jpg',
        CAST(N'2024-06-30T04:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        22,
        2,
        5,
        N'Genre Analysis: Isekai Anime',
        NULL,
        N'AnimeWorld',
        N'isekai-anime-genre.jpg',
        CAST(N'2024-07-01T05:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        23,
        3,
        5,
        N'Genre Analysis: Slice of Life Anime',
        NULL,
        N'AnimeSlice',
        N'slice-of-life-anime-genre.jpg',
        CAST(N'2024-07-02T06:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        24,
        4,
        5,
        N'Genre Analysis: Mecha Anime',
        NULL,
        N'AnimeMech',
        N'mecha-anime-genre.jpg',
        CAST(N'2024-07-03T07:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        25,
        5,
        5,
        N'Genre Analysis: Psychological Anime',
        NULL,
        N'AnimePsyche',
        N'psychological-anime-genre.jpg',
        CAST(N'2024-07-04T08:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        26,
        1,
        6,
        N'History and Evolution: Gundam Series',
        NULL,
        N'AnimeHistory',
        N'gundam-series-history.jpg',
        CAST(N'2024-07-05T09:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        27,
        2,
        6,
        N'History and Evolution: Dragon Ball Franchise',
        NULL,
        N'AnimeChronicles',
        N'dragon-ball-evolution.jpg',
        CAST(N'2024-07-06T10:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        28,
        3,
        6,
        N'History and Evolution: Studio Ghibli Films',
        NULL,
        N'AnimeRetro',
        N'studio-ghibli-history.jpg',
        CAST(N'2024-07-07T11:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        29,
        4,
        6,
        N'History and Evolution: Evangelion Franchise',
        NULL,
        N'AnimeEva',
        N'evangelion-evolution.jpg',
        CAST(N'2024-07-08T12:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        30,
        5,
        6,
        N'History and Evolution: Pokémon Series',
        NULL,
        N'AnimeMon',
        N'pokemon-evolution.jpg',
        CAST(N'2024-07-09T13:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        31,
        1,
        7,
        N'Top 10 Anime Openings of All Time',
        NULL,
        N'AnimeRankings',
        N'top-10-anime-openings.jpg',
        CAST(N'2024-07-10T14:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        32,
        2,
        7,
        N'Top 5 Anime Films You Must Watch',
        NULL,
        N'AnimeReviewsPlus',
        N'top-5-anime-films.jpg',
        CAST(N'2024-07-11T15:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        33,
        3,
        7,
        N'Top 15 Anime Villains That Stole the Show',
        NULL,
        N'AnimeFanatics',
        N'top-15-anime-villains.jpg',
        CAST(N'2024-07-12T16:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        34,
        4,
        7,
        N'Top 20 Anime Endings that Left Us Speechless',
        NULL, N'AnimeEndings', N'top-20-anime-endings.jpg',
        CAST(N'2024-07-13T17:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        35,
        5,
        7,
        N'Top 25 Anime Soundtracks of All Time',
        NULL,
        N'AnimeMusic',
        N'top-25-anime-soundtracks.jpg',
        CAST(N'2024-07-14T18:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        36,
        1,
        8,
        N'Exclusive Interview with Naoko Takeuchi',
        NULL,
        N'AnimeMasters',
        N'naoko-takeuchi-interview.jpg',
        CAST(N'2024-07-15T19:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        37,
        2,
        8,
        N'Interview: Makoto Shinkai on His Latest Anime Film',
        NULL,
        N'AnimeDirect',
        N'makoto-shinkai-interview.jpg',
        CAST(N'2024-07-16T20:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        38,
        3,
        8,
        N'Interview: Hayao Miyazaki Reflects on Studio Ghibli',
        NULL,
        N'AnimeLegends',
        N'hayao-miyazaki-interview.jpg',
        CAST(N'2024-07-17T21:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        39,
        4,
        8,
        N'Interview: Jun Maeda on His Approach to Anime Music',
        NULL,
        N'AnimeMelodies',
        N'jun-maeda-interview.jpg',
        CAST(N'2024-07-18T22:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        40,
        1,
        9,
        N'Art Analysis: The Beauty of Anime Landscapes',
        NULL,
        N'AnimeArtistry',
        N'anime-landscapes-analysis.jpg',
        CAST(N'2024-07-19T23:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        41,
        2,
        9,
        N'Art Analysis: Character Design in Anime',
        NULL,
        N'AnimeDesigns',
        N'anime-character-design.jpg',
        CAST(N'2024-07-20T00:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        42,
        3,
        9,
        N'Art Analysis: Evolution of Anime Art Styles',
        NULL,
        N'AnimeStyles',
        N'evolution-of-anime-art.jpg',
        CAST(N'2024-07-21T01:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        43,
        4,
        9,
        N'Art Analysis: Studio Ghibli Animation Techniques',
        NULL,
        N'AnimeGhibli',
        N'studio-ghibli-animation.jpg',
        CAST(N'2024-07-22T02:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        44,
        5,
        9,
        N'Art Analysis: Makoto Shinkai''s Visual Storytelling',
        NULL,
        N'AnimeVisuals',
        N'makoto-shinkai-visuals.jpg',
        CAST(N'2024-07-23T03:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        45,
        1,
        10,
        N'Question: What Makes a Good Anime Series?',
        NULL,
        N'AnimeQuestions',
        N'good-anime-series.jpg',
        CAST(N'2024-07-24T04:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        46,
        2,
        10,
        N'Question: How Does Anime Influence Global Culture?',
        NULL,
        N'AnimeInfluence',
        N'anime-global-culture.jpg',
        CAST(N'2024-07-25T05:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        47,
        3,
        10,
        N'Question: Why Do People Love Anime?',
        NULL,
        N'AnimeLove',
        N'why-people-love-anime.jpg',
        CAST(N'2024-07-24T06:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        48,
        4,
        10,
        N'Question: The Future of Anime Industry?',
        NULL,
        N'AnimeFuture',
        N'future-of-anime-industry.jpg',
        CAST(N'2024-07-24T07:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        49,
        5,
        10,
        N'Question: Impact of Anime Streaming Platforms',
        NULL,
        N'AnimeStreaming',
        N'anime-streaming-impact.jpg',
        CAST(N'2024-07-24T08:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        50,
        1,
        11,
        N'Community Article: Favorite Anime Memories',
        NULL,
        N'AnimeMemories',
        N'favorite-anime-memories.jpg',
        CAST(N'2024-07-24T09:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        51,
        2,
        11,
        N'Community Article: Anime Recommendations from Fans',
        NULL,
        N'AnimeFansChoice',
        N'anime-recommendations-fans.jpg',
        CAST(N'2024-07-24T10:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        52,
        3,
        11,
        N'Community Article: How Anime Changed My Life',
        NULL,
        N'AnimeLifeChanges',
        N'anime-changed-my-life.jpg',
        CAST(N'2024-07-24T11:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        53,
        4,
        11,
        N'Community Article: Anime Fanart Showcase',
        NULL,
        N'AnimeFanart',
        N'anime-fanart-showcase.jpg',
        CAST(N'2024-07-24T12:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
INSERT [dbo].[Post]
    ([PostID], [UserID], [CategoryID], [Title], [Content], [Source], [Image], [PostTime], [Status], [Vote], [View])
VALUES
    (
        54,
        5,
        11,
        N'Community Article: Best Anime Cosplays of the Year',
        N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla gravida fringilla lectus, id bibendum ligula suscipit id. Nam sodales tortor eget nisl congue, vel accumsan nisi gravida. Proin vehicula elit ac metus hendrerit, vel euismod libero dictum. Mauris tristique convallis odio id vehicula. Quisque condimentum erat in libero auctor, ac ullamcorper justo elementum. Phasellus ultricies vestibulum nisi, eu fermentum quam faucibus sed. In hac habitasse platea dictumst. Nam volutpat justo et dolor porttitor venenatis. Nulla eu ex nec metus posuere blandit a sit amet sem. Vivamus blandit pharetra ligula, id posuere lacus cursus et. Proin feugiat luctus velit, id scelerisque turpis condimentum sit amet. Nam non eleifend lorem. Etiam id odio vel ipsum feugiat aliquet id in urna. 
        In efficitur ligula et nisi eleifend bibendum. Sed fermentum tortor a velit convallis, nec vestibulum sem tincidunt. Aliquam pulvinar maximus venenatis. Mauris vel volutpat nisi. Nunc at sapien eget est dictum dignissim. Duis a dui eros. Aenean ut quam vel ligula venenatis tincidunt. Phasellus id nisl nec arcu rhoncus molestie. Nulla suscipit libero id augue blandit, quis auctor ex ultricies. Fusce ullamcorper est in massa scelerisque, non mollis turpis posuere. In nec felis eget orci pretium blandit sit amet et nisl. Morbi sollicitudin sit amet nisi eget fringilla. Suspendisse interdum arcu at nibh laoreet, vitae lacinia eros sagittis. Quisque faucibus, mi nec efficitur suscipit, ligula neque fermentum metus, sed tincidunt libero lacus non magna. 
        Sed at libero et nisl fermentum aliquet. Donec dignissim justo eget nisl rhoncus molestie. Nullam tristique posuere quam, eu varius risus finibus et. Donec vel ligula at erat vestibulum sollicitudin. Sed in fringilla metus. Sed lacinia malesuada orci, nec viverra erat mollis vel. Praesent nec orci sodales, tempor orci eget, consequat lectus. Sed lobortis nisl id arcu auctor, a congue libero fermentum. Cras ut ex id dui egestas ultrices. Integer mollis id turpis nec congue. Maecenas auctor vel nisi ac aliquet. Aliquam sit amet velit varius, placerat nisl sit amet, venenatis purus. Donec maximus nulla eget turpis tempus, a rhoncus magna iaculis.
        Quisque tincidunt fringilla nulla vel tincidunt. Proin semper auctor arcu non pellentesque. Nam non elit nec ipsum consectetur pretium. Vestibulum tincidunt eros vel elit dapibus, sed tempor felis posuere. Nulla facilisi. Duis consequat dui at massa dapibus ultricies. In ac ligula in diam sollicitudin feugiat. Suspendisse at sodales lectus. Curabitur auctor justo at odio dictum, eu tincidunt nulla feugiat. Sed ultricies nulla ac nisi vehicula, quis euismod magna consectetur. Curabitur consequat a odio a convallis. Donec lacinia ex justo, eu aliquet ex efficitur a. Nulla facilisi. Integer varius nisi nec nisi tempor, sit amet lacinia lorem egestas.
        Praesent eu condimentum enim. Fusce sit amet tortor leo. Ut vitae molestie velit, sed iaculis lacus. Integer in lectus nec leo placerat consequat. Nunc sagittis risus quis bibendum aliquam. Suspendisse potenti. In fermentum diam quis sapien aliquam iaculis. Cras dignissim libero nec nisl molestie, ut dignissim lectus ultricies. Fusce non lectus elit. In eget dictum nisi. Curabitur eu lectus vel lacus rutrum sollicitudin a non justo. Nam a sem vel justo mollis feugiat. Nam ullamcorper bibendum sapien a ultricies.
        Vestibulum auctor venenatis eros in consequat. Nam interdum, lorem nec luctus tristique, ante quam pretium justo, nec dapibus urna purus ac justo. Proin eleifend orci nec sodales consectetur. Ut ac tincidunt magna. Aliquam et dolor sit amet sapien pretium aliquam. Morbi a ligula luctus, faucibus orci in, lobortis erat. Proin et purus at nisl congue viverra. Sed vehicula hendrerit tortor, eget fermentum lorem convallis eget. Donec consequat, elit ut tempor euismod, erat erat blandit magna, nec facilisis erat purus non dui. Morbi et urna vel purus vestibulum hendrerit in ut ex. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi a ipsum ut mi tempor tincidunt.
        Sed ultrices ut velit quis auctor. Cras ut semper ex. Nullam vel lacus non lectus interdum tincidunt nec non libero. Sed vitae ligula eu tellus dapibus ullamcorper in ac arcu. Sed vel eros non nisl ultrices accumsan. Sed vel sem id quam venenatis efficitur. In efficitur velit at scelerisque congue. Cras efficitur, felis nec sollicitudin tempus, ligula risus feugiat risus, quis egestas risus est sed tortor. Ut malesuada, risus eget bibendum cursus, sapien arcu consequat eros, sed pharetra quam velit et risus. Nam laoreet ipsum eu ipsum bibendum, nec dignissim erat fermentum. Curabitur convallis tellus in erat gravida, vitae gravida arcu venenatis. Phasellus pretium arcu vel eros congue dictum. Proin convallis lobortis lorem, nec rhoncus urna ultrices et. Proin sollicitudin aliquet pretium.
        Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Mauris dictum blandit diam ac tincidunt. Proin eu diam magna. Duis ut mi nisi. Suspendisse vel nisi ut quam dapibus egestas. Sed vel massa a dui sollicitudin molestie a nec nisi. Curabitur eleifend sem nec dui dictum luctus. Proin pellentesque, felis vel dapibus venenatis, erat augue auctor magna, eget bibendum felis est ac nisl. Nam luctus in justo sed lacinia. Vivamus sem nisi, euismod sed nunc et, efficitur tincidunt nulla. Integer nec risus lectus. Proin lacinia semper ultrices. Vivamus tempus, risus et mollis pellentesque, nisl ipsum vehicula nisl, eget congue orci ex eget purus. Nulla facilisi. Integer vulputate quam in accumsan feugiat. Proin id tortor ut urna tempor tempor. Cras non semper sem. Sed sit amet libero nec eros efficitur auctor. Sed vitae consequat justo, eu malesuada lacus. Donec euismod dapibus orci ac tempor.
        Curabitur id libero vel urna varius ultricies. Curabitur viverra, nisl ac fringilla aliquet, ex odio interdum risus, a efficitur eros metus sit amet felis. Sed interdum metus eu sollicitudin placerat. Proin eget sodales turpis. Nam volutpat mi sed libero tincidunt suscipit. Suspendisse potenti. Nullam vel rutrum nisi. Aliquam dignissim, ligula nec vestibulum varius, nisl sem elementum lectus, ac auctor ipsum purus sed velit. Phasellus id risus vel elit ultricies accumsan. Phasellus feugiat vestibulum risus, in viverra mi lobortis in. Nulla vehicula tortor at justo sollicitudin, eget fermentum nisl viverra. Curabitur rutrum bibendum velit eget tinc',
        N'AnimeCosplay',
        N'best-anime-cosplays.jpg',
        CAST(N'2024-07-24T13:00:00.000' AS DateTime),
        N'active',
        0,
        0
    )
SET IDENTITY_INSERT [dbo].[Post] OFF
GO


INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (1, 9)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (2, 11)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (3, 8)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (4, 6)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (5, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (6, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (7, 9)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (8, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (9, 15)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (10, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (11, 10)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (12, 4)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (13, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (14, 15)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (15, 15)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (16, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (17, 8)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (18, 4)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (19, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (20, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (21, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (22, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (23, 2)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (24, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (25, 9)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (26, 1)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (27, 15)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (28, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (29, 15)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (30, 13)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (31, 14)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (32, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (33, 9)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (34, 10)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (35, 14)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (36, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (37, 10)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (38, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (39, 14)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (40, 14)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (41, 14)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (42, 14)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (43, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (44, 10)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (45, 10)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (46, 10)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (47, 9)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (48, 15)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (49, 15)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (50, 13)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (51, 3)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (52, 13)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (53, 14)
INSERT [dbo].[PostGenre]
    ([PostID], [GenreID])
VALUES
    (54, 10)
GO


SET IDENTITY_INSERT [dbo].[Rank] ON
INSERT INTO [dbo].[Rank] ([RankID], [Name], [PointsRequired], [Color])
VALUES
    (1, N'Newbie', 0, N'#2a6b2b'),
    (2, N'Wibu', 20, N'#b03f28'),
    (3, N'Otaku Pro', 50, N'#2828b0'),
    (4, N'Hardcore Otaku', 70, N'#9e207a'),
    (5, N'Ultimate Otaku', 100, N'#e0de51');
SET IDENTITY_INSERT [dbo].[Rank] OFF
GO


SET IDENTITY_INSERT [dbo].[Role] ON
INSERT [dbo].[Role]
    ([RoleID], [RoleName])
VALUES
    (
        1,
        N'Admin'
    )
INSERT [dbo].[Role]
    ([RoleID], [RoleName])
VALUES
    (
        2,
        N'Mod'
    )
INSERT [dbo].[Role]
    ([RoleID], [RoleName])
VALUES
    (
        3,
        N'Member'
    )
SET IDENTITY_INSERT [dbo].[Role] OFF
GO


SET IDENTITY_INSERT [dbo].[User] ON
INSERT [dbo].[User]
    ([UserID], [Username], [Password], [RoleID], [Point], [Status], [Email], [Fullname], [RankID], [ProfilePhoto], [PhoneNumber], [DateOfBirth], [CreationDate], [Bio])
VALUES
    (
        1,
        N'minhtvanh',
        N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
        1,
        100,
        N'active',
        N'minhtvanh01@gmail.com',
        N'pham vu anh minh',
        5,
        N'0',
        NULL,
        NULL,
        CAST(N'2024-06-06T17:47:00.660' AS DateTime),
        NULL
    )
INSERT [dbo].[User]
    ([UserID], [Username], [Password], [RoleID], [Point], [Status], [Email], [Fullname], [RankID], [ProfilePhoto], [PhoneNumber], [DateOfBirth], [CreationDate], [Bio])
VALUES
    (
        2,
        N'minhpham',
        N'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',
        3,
        0,
        N'active',
        N'minhtvanh2006@gmail.com',
        N'anh minh',
        1,
        NULL,
        NULL,
        NULL,
        CAST(N'2024-06-06T17:47:00.693' AS DateTime),
        NULL
    )
INSERT [dbo].[User]
    ([UserID], [Username], [Password], [RoleID], [Point], [Status], [Email], [Fullname], [RankID], [ProfilePhoto], [PhoneNumber], [DateOfBirth], [CreationDate], [Bio])
VALUES
    (
        3,
        N'quocanh',
        N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
        1,
        100,
        N'active',
        N'minhtvanh01@gmail.com',
        N'qu?c',
        5,
        NULL,
        NULL,
        NULL,
        CAST(N'2024-06-06T17:47:00.693' AS DateTime),
        NULL
    )
INSERT [dbo].[User]
    ([UserID], [Username], [Password], [RoleID], [Point], [Status], [Email], [Fullname], [RankID], [ProfilePhoto], [PhoneNumber], [DateOfBirth], [CreationDate], [Bio])
VALUES
    (
        4,
        N'quocanh12',
        N'91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203',
        2,
        20,
        N'active',
        N'quocanh1502@gmail.com',
        N'quoc anh12',
        2,
        NULL,
        NULL,
        NULL,
        CAST(N'2024-06-06T17:47:00.693' AS DateTime),
        NULL
    )
INSERT [dbo].[User]
    ([UserID], [Username], [Password], [RoleID], [Point], [Status], [Email], [Fullname], [RankID], [ProfilePhoto], [PhoneNumber], [DateOfBirth], [CreationDate], [Bio])
VALUES
    (
        5,
        N'quocanh123',
        N'91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203',
        2,
        20,
        N'active',
        N'quocanh@gmail.com',
        N'quoc anh',
        2,
        NULL,
        NULL,
        NULL,
        CAST(N'2024-06-06T17:47:00.693' AS DateTime),
        NULL
    )
SET IDENTITY_INSERT [dbo].[User] OFF
GO


ALTER TABLE [dbo].[Comment] ADD DEFAULT ((0)) FOR [Vote]
GO
ALTER TABLE [dbo].[Comment] ADD DEFAULT (getdate()) FOR [CreateAt]
GO

--ALTER TABLE [dbo].[Media] ADD DEFAULT (getdate()) FOR [Uploaded]
--GO

ALTER TABLE [dbo].[Post] ADD DEFAULT ((0)) FOR [Vote]
GO
ALTER TABLE [dbo].[Post] ADD DEFAULT ((0)) FOR [View]
GO

ALTER TABLE [dbo].[User] ADD DEFAULT (getdate()) FOR [CreationDate]
GO


ALTER TABLE [dbo].[Anime] WITH CHECK ADD CONSTRAINT [FK_Anime_Genre] FOREIGN KEY ([GenreId])
REFERENCES [dbo].[Genre] ([GenreID])
GO
ALTER TABLE [dbo].[Anime] CHECK CONSTRAINT [FK_Anime_Genre]
GO

ALTER TABLE [dbo].[Comment] WITH CHECK ADD FOREIGN KEY([PostID])
REFERENCES [dbo].[Post] ([PostID])
GO
ALTER TABLE [dbo].[Comment] WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Comment] WITH CHECK ADD CONSTRAINT [FK_Comment_Comment] FOREIGN KEY ([ParentId])
REFERENCES [dbo].[Comment] ([CommentID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Comment]
GO

ALTER TABLE [dbo].[GenreAnime] WITH CHECK ADD FOREIGN KEY ([AnimeID])
REFERENCES [dbo].[Anime] ([AnimeID])
GO
ALTER TABLE [dbo].[GenreAnime] WITH CHECK ADD FOREIGN KEY ([GenreID])
REFERENCES [dbo].[Genre] ([GenreID])
GO

--ALTER TABLE [dbo].[Media] WITH CHECK ADD FOREIGN KEY ([UserID])
--REFERENCES [dbo].[User] ([UserID])
--GO

ALTER TABLE [dbo].[Post] WITH CHECK ADD FOREIGN KEY ([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Post] WITH CHECK ADD FOREIGN KEY ([UserID])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[PostGenre] WITH CHECK ADD FOREIGN KEY ([GenreID])
REFERENCES [dbo].[Genre] ([GenreID])
GO
ALTER TABLE [dbo].[PostGenre] WITH CHECK ADD FOREIGN KEY ([PostID])
REFERENCES [dbo].[Post] ([PostID])
GO

ALTER TABLE [dbo].[Report] WITH CHECK ADD FOREIGN KEY ([PostID])
REFERENCES [dbo].[Post] ([PostID])
GO
ALTER TABLE [dbo].[Report] WITH CHECK ADD FOREIGN KEY ([UserID])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[Ticket] WITH CHECK ADD FOREIGN KEY ([UserID])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[Ban] WITH CHECK ADD FOREIGN KEY ([BannedUserId])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Ban] WITH CHECK ADD FOREIGN KEY ([BanSourceId])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[User] WITH CHECK ADD FOREIGN KEY ([RankID])
REFERENCES [dbo].[Rank] ([RankID])
GO
ALTER TABLE [dbo].[User] WITH CHECK ADD FOREIGN KEY ([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO

--ALTER TABLE [dbo].[VoteUserCmt] WITH CHECK ADD FOREIGN KEY ([CommentID])
--REFERENCES [dbo].[Comment] ([CommentID])
--GO
--ALTER TABLE [dbo].[VoteUserCmt] WITH CHECK ADD FOREIGN KEY ([UserID])
--REFERENCES [dbo].[User] ([UserID])
--GO

ALTER TABLE [dbo].[VoteUserPost] WITH CHECK ADD FOREIGN KEY ([PostID])
REFERENCES [dbo].[Post] ([PostID])
GO
ALTER TABLE [dbo].[VoteUserPost] WITH CHECK ADD FOREIGN KEY ([UserID])
REFERENCES [dbo].[User] ([UserID])
GO


ALTER TABLE [dbo].[Anime] WITH CHECK ADD CHECK (([Status]='Ongoing' OR [Status]='Completed' OR [Status]='Released'))
GO

ALTER TABLE [dbo].[Comment] WITH CHECK ADD CHECK (([Status]='deactive' OR [Status]='active' OR [Status]='hide'))
GO

ALTER TABLE [dbo].[Post] WITH CHECK ADD CHECK (([Status]='deactive' OR [Status]='active' OR [Status]='hide'))
GO

ALTER TABLE [dbo].[User] WITH CHECK ADD CHECK (([Status]='deactive' OR [Status]='active' OR [Status]='hide'))
GO

--ALTER TABLE [dbo].[VoteUserCmt] WITH CHECK ADD CONSTRAINT [CK_Status_VoteUserCmt] CHECK (([Status]='upvote' OR [Status]='downvote'OR [Status]='unvote'))
--GO
--ALTER TABLE [dbo].[VoteUserCmt] CHECK CONSTRAINT [CK_Status_VoteUserCmt]
--GO

ALTER TABLE [dbo].[VoteUserPost] WITH CHECK ADD CONSTRAINT [CK_Status_VoteUserPost] CHECK (([Status]='upvote' OR [Status]='downvote'OR [Status]='unvote'))
GO
ALTER TABLE [dbo].[VoteUserPost] CHECK CONSTRAINT [CK_Status_VoteUserPost]
GO

ALTER TABLE [dbo].[Report] WITH CHECK ADD CHECK (([Status]='Pending' OR [Status]='Approved' OR [Status]='Rejected'))
GO

ALTER TABLE [dbo].[Ticket] WITH CHECK ADD CHECK (([Status]='Pending' OR [Status]='Approved' OR [Status]='Rejected'))
GO

CREATE PROCEDURE UpdateUserStatus
AS
BEGIN
    SET NOCOUNT ON;

    -- Update user status to 'active' where all bans have expired
	update [user]
     SET status = 'active'
    WHERE userid IN (
        SELECT BannedUserId
        FROM Ban
        GROUP BY BannedUserId
        HAVING MAX(ExpireDate) < GETDATE()
    )
    AND status = 'deactive';
END
GO

CREATE TRIGGER trg_update_rankid
ON [user]
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Update the rankid based on the new points
    UPDATE u
    SET rankid = (
        SELECT TOP 1 r.rankid
        FROM [rank] r
        WHERE r.pointsrequired <= u.point
        ORDER BY r.pointsrequired DESC
    )
    FROM [user] u
    JOIN inserted i ON u.userid = i.userid;
END
GO

update [rank] set pointsrequired = 0 where  rankid = 1
go
create table [Notification](
notificationId int primary key identity(1,1),
sourcePostId int foreign key references post(postid),
sourceUserId int foreign key references [user](userid),
targetUserId int foreign key references [user](userid),
content nvarchar(255),
timeCreated datetime,
)
go

--create table Ban(
--[BanId] int identity(1,1) primary key,
--[BannedUserId] int foreign key references [user](userid),
--[BanSourceId] int foreign key references [user](userid),
--[BanReason] nvarchar(255),
--[BannedDate] datetime,
--[ExpireDate] datetime,
--)

CREATE TRIGGER trg_UpdatePostStatusAndBanOnReport
ON [dbo].[Report]
AFTER INSERT
AS
BEGIN
    -- Declare variables to hold the PostID, View count, Report count, BannedUserId, and BanDuration
    DECLARE @PostID INT, @ViewCount INT, @ReportCount INT, @BannedUserId INT, @BanDuration INT;

    -- Cursor to iterate through inserted rows
    DECLARE cur CURSOR FOR
    SELECT PostID
    FROM inserted;

    OPEN cur;

    FETCH NEXT FROM cur INTO @PostID;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Get the view count of the post
        SELECT @ViewCount = [View] FROM [dbo].[Post] WHERE PostID = @PostID;

        -- Get the report count for the post
        SELECT @ReportCount = COUNT(*) FROM [dbo].[Report] WHERE PostID = @PostID;

        -- Get the UserID of the post to be banned
        SELECT @BannedUserId = UserID FROM [dbo].[Post] WHERE PostID = @PostID;

        -- Initialize @BanDuration to NULL
        SET @BanDuration = NULL;

        -- Condition for posts with less than 20 views and 3 or more reports
        IF @ViewCount < 20 AND @ReportCount >= 3
        BEGIN
            -- Ban logic based on the number of reports
            IF @ReportCount >= 3 AND @ReportCount <= 10
            BEGIN
                SET @BanDuration = 1; -- 1 hour
            END
            ELSE IF @ReportCount > 10 AND @ReportCount <= 20
            BEGIN
                SET @BanDuration = 3; -- 3 hours
            END
            ELSE IF @ReportCount > 20 AND @ReportCount <= 30
            BEGIN
                SET @BanDuration = 24; -- 1 day (24 hours)
            END
            ELSE IF @ReportCount > 30 AND @ReportCount <= 50
            BEGIN
                SET @BanDuration = 120; -- 5 days (120 hours)
            END
            ELSE IF @ReportCount > 50
            BEGIN
                SET @BanDuration = 168; -- 1 week (168 hours)
            END

            -- Update post status to 'deactive'
            UPDATE [dbo].[Post]
            SET Status = 'deactive'
            WHERE PostID = @PostID;

            -- Update user status to 'deactive'
            UPDATE [dbo].[User]
            SET Status = 'deactive'
            WHERE UserID = @BannedUserId;
        END
        -- Condition for posts with 20 or more views and report to view ratio >= 10%
        ELSE IF @ViewCount >= 20 AND (@ReportCount * 1.0 / @ViewCount) >= 0.1
        BEGIN
            -- Ban logic based on the number of reports
            IF @ReportCount >= 3 AND @ReportCount <= 10
            BEGIN
                SET @BanDuration = 1; -- 1 hour
            END
            ELSE IF @ReportCount > 10 AND @ReportCount <= 20
            BEGIN
                SET @BanDuration = 3; -- 3 hours
            END
            ELSE IF @ReportCount > 20 AND @ReportCount <= 30
            BEGIN
                SET @BanDuration = 24; -- 1 day (24 hours)
            END
            ELSE IF @ReportCount > 30 AND @ReportCount <= 50
            BEGIN
                SET @BanDuration = 120; -- 5 days (120 hours)
            END
            ELSE IF @ReportCount > 50
            BEGIN
                SET @BanDuration = 168; -- 1 week (168 hours)
            END

            -- Update post status to 'deactive'
            UPDATE [dbo].[Post]
            SET Status = 'deactive'
            WHERE PostID = @PostID;

            -- Update user status to 'deactive'
            UPDATE [dbo].[User]
            SET Status = 'deactive'
            WHERE UserID = @BannedUserId;
        END

        -- Insert ban record if applicable
        IF @BanDuration IS NOT NULL
        BEGIN
            INSERT INTO [dbo].[Ban] (BannedUserId, BanSourceId, BanReason, BannedDate, ExpireDate, SourcePostId)
            VALUES (
                @BannedUserId,
                NULL, -- BanSourceId is set to NULL when triggered
                'Reported too many times',
                GETDATE(),
                DATEADD(HOUR, @BanDuration, GETDATE()),
                @PostID
            );
			  INSERT INTO [dbo].[Notification]
            VALUES (
                Null,
                NULL, 
				@BannedUserId,
                'You got banned for getting reported too many times',
                GETDATE()
            );
        END

        FETCH NEXT FROM cur INTO @PostID;
    END;

    CLOSE cur;
    DEALLOCATE cur;
END;
GO
update post set content = 'This is a post content' where postid < 54
USE [master]
GO
ALTER DATABASE [SWP391_SU24] SET READ_WRITE 
GO
