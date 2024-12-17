-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2024 at 11:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `job_finder_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(127) NOT NULL,
  `name` varchar(127) NOT NULL,
  `email` varchar(127) NOT NULL,
  `password` varchar(127) NOT NULL,
  `profile_pic` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`, `profile_pic`) VALUES
(2, 'Job Finder', 'jobfinder@gmail.com', 'jobfinder', 0xffd8ffe000104a46494600010200000100010000ffdb004300080606070605080707070909080a0c140d0c0b0b0c1912130f141d1a1f1e1d1a1c1c20242e2720222c231c1c2837292c30313434341f27393d38323c2e333432ffdb0043010909090c0b0c180d0d1832211c213232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232ffc000110800c800c803012200021101031101ffc4001f0000010501010101010100000000000000000102030405060708090a0bffc400b5100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9faffc4001f0100030101010101010101010000000000000102030405060708090a0bffc400b51100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00f9fe8a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a28a0028a5a280128a5a280128a5c52500145145001451450014514500145145001451450014514500145145001451450014514500145145002e68a4a5a002969296810521a534940c4a28a2800a28a2800a28a2800a28a2800a28a2800a28a2800a28a2800a28a2800ab9a76997dabdec765a75a4d757327dd8a142cc7f2edef5bbe06f03ea5e39d6859598f2ade3c35cdd30cac4bfd58f61dfe809af6ad5fc51e11f831a61d1741b34bcd659419416f9b3d9a67fd428fd339a00e3f40fd9eb5cbe8d65d6b50b7d354f3e546be7483d8e0851f8135d09f837f0e34dfddea9e2c91661c1125fc117e8573fad793788be2178a7c592b8bfd4e6f21ba5adb931c407a6d1d7ea726b9a104a7fe593ffdf26803dfc7c14f00eadf268de2b95e63d025dc338fc9403fad72be22f803e24d2a379f49b883568579d8a3ca971fee9383f8367dabca5a29631b9a3751ea4115d8785fe2a78afc2d2a2c1a8bddda2f5b5bb2644c7a02795fc08a40725756b71657325b5d412413c676bc52a15653e841e45435f4ac371e0df8e7a3b432c434fd7e08f23a79b1fb83c7991e7b76f6c835e07e2af0bea5e10d725d2b548b6ca9f3248bf7254ecca7b83fa74a00c5a28a2980945145001451450014514500145145001451450014514500145145001562c6cae352bfb7b2b48cc9717122c51a0fe2663802a0c57abfc02d05752f1b4da9ca9ba3d3602eb9ed23fcabfa6f3f8500775e20d4ad3e0bfc39b5d1f4a31b6b5780e25c725f037ca47a0e0283edd706be729e796e679279e47966918bbc8e72cc4f2493dcd761f147c44fe24f1fea5701cb5bdb486d6dc7608848c8fa9dcdf8d7186803d17e096a563a57c40173a85e5bda41f64957cc9e408b93b70326bea1d3757d375889e5d32fed6f2346dacf6f2ac814f5c120f5af86057d21fb3a0ff8a5356ffafe1ffa02d31167f682beb45f03c3606e61178f771cab0171bca00e0b05eb8cf7af996bea7f899f0aaebc7bad5a5fc1aa4368b05bf9252488b1277139e0fbd713ff000cdfa8ff00d0c76bff0080cdfe348678d699a95e68fa8c1a8585c3dbdd40e1e3910f20ff0087b77afa2afa2b2f8ddf0b3edb0451c7afd803851d56603253fdc718c7be3d0d78cfc41f01cfe00d4ed2ca7bf8ef1ae21f383246502fcc463927d2ba0f815e237d1bc7d1e9eee45aea8860719e3cc00b21fae72bff0002a00f32656462ac0ab29c10460834daef7e31e829a0fc49d41624d905e0179181fede777fe3e1ab823400945145001451450014514500145145001451450014514500140a28a0070afa03f67f02d3c29e23d4140de2551ff7c2161ffa11af9fabdf7f6789e3b9d23c45a5b9e4b46f8f50caca7ff411f9d0078333b3b33b12598e493dcd6d784fc2b7fe32d6c693a749025c18da5ccec5570319e403eb593776d2595e4f6b30db2c32346e3d08383fcabbdf829a9d9697f11ed9ef6e12049a078119ce0176c6067b671408ade2cf84faff0083745fed5d4ae2c24b7f3562c41233364e71c151e95ea9fb39ff00c8a9abff00d7f0ff00d016bd435df0f699e27d29f4dd5edbed16aec1ca6e2a411d08208229be1af0ae8de12d3decb45b4fb3c323f98e0bb3966c6324b127a0a00a1e3ef144de0ff08dd6b505b4771242d1a88e46201dcc17a8fad73de00f8ad65e28d2aeaeb599f4dd2a68a7f2d227ba552ebb41ddf3107a923f0aedf5ad1b4ed7f4e934ed52d96e6d2420b44c48048391d083d457ccdf1b7c37a3f867c4da7dae8d629690c967e63a2331cb6f619e49ec0503343e3fea961aa78974a934fbeb6bb8d2ccab35bcab200779e0904d79cf85ae5ecfc5ba35ca1c3457d0b8fc1c1ac8adef04d83ea7e39d0ecd067ccbe8b77fba18163f903401ea3fb485baaeb9a15c81f3c96d2464fb2b023ff4235e206bda3f68cbd597c4fa459039682ccc87db7b91ff00b2578c1a0625145140828a28a0028a28a0028a28a0028a28a0028a28a0028a28a002bd0fe0c78913c3ff00102d9277096da829b49093c06620a1ff00be801f89af3ca5562ac082411c823b5007a67c6cf0b3e83e3797508e322cb54cce8c07024ff968bf5cfcdff02af3426be8ff000dea7a6fc65f87d2681abca23d6ed101327f16e1c2cca3b83d187b9e9915e0be24f0dea7e15d625d3355b7314e872add5645ecca7b83fe79a00bb1fc41f18451ac69e25d502280aa3ed2dc01f8d7a37c1cf12f8b75cf1c402fb53d52f74d48e413191dde256d876ee3d01cf4af15af68f821e2ed07c35a66af16b1a9c566f34d1b462407e6014e7a0a00f56f8aefaaafc3ebf6d15af16f83c450d996f331bc671b79e99cfb57c9ba8eada8eaf32cda95fdcde4a8bb55ee2569081d70093d2beaeb8f8a5e097b5954788ad4b14200c373c7d2be45a0482bd9bf67ef0bb5d6bd73e26b94db6b60862819ba19587cc7f05273fef0af3cf06782f54f1b6b496361195854837172c3e4857d4fa9f41dff00335ec3f133c4ba6fc3ef0643e05f0eb05ba961d93b29f9a28dbef163fdf7c9fc093c7140cf24f889e235f14f8eb53d4e36dd6cd27956e7fe99a0daa7f1c67f1ae5a8a2800a28a2800a28a2800a28a2800a28a2800a28a2800a28a2800a28a2800a28a2802fe91abdfe85a9c1a8e9b72f6f75036e4917f911dc1ee0f5af7cd27c77e0ef8a5a447a2f8bede0b3d48708ecdb14b7f7a293f849fee9fa7cd5e0fa1e83a9f88f511a7e936ad737454b88d5954e07539240adbb3f86de30bed2bfb4edb41b992d3058382bb980eeab9dcc3e839a00edbc49fb3f6b369234de1ebc8750b73cac53308a51ed9fba7eb91f4ae0eebe1cf8cece4292f86752623bc501947e6b915d7787edbe2d787b448b52d212fbfb28c02e111a48e64f2c8dc0846248e3d003535bfed09e2b8902cd61a4ce47f1189d49fc9f1fa50071d65f0cfc6b7f20487c377e84f79e3f247e6f8af44f0c7ecf57b34893f896fd2de11c9b6b43b9dbd8b9181f866b22eff682f16ce8560b5d2edb3fc490bb30ff00be988fd2aefc31f88be2ed67e235b47a85cde6a76d728d1cf1227c90a9c624daa00500e327d09a00ea7c73e3ad3be16582f85bc2da50b7bd310712327eee30dc6fc9e646e3a9e38e738c57cef7777717f772ddddccf35c4cc5e49246cb331ea49afaf3c77f0db4af1dfd9e5bc96682eada39122922206770e03647201e71f5f5af91f51b0b8d2f51b9b0bb8cc7716d2345221ecca706802ad14514005145140051451400514514005145140051451400514514005145140051451401ea9f082f34ad026b8f10dd59eb7757d0ef8608acad4c91382a32090386fc7b8aef34bf1fe9ab1e8da9dee97e258754d36c5ed869f0db3793360052dd3b607a633ed5ccfc3bf1a695e15f849a89ba9d65bb1a8965b286f7c89dd48886e5c7cd8ebd3d0d777e19f1be812af86eeeef5db0864fb0dd19d67bd4dd1bbbc442b9273bb83d7ae0d2028d87c48d165f0ed833691aacd7274a36bb2df4e3b9db6aee11cd9c850476f63dabe7a3a36a82ec5a1d36f05c95dc21f21b791ebb719c57d3fa6f8dfc1b33f87ae6cb55d3ac6db65c48f6f2dca21819c64ab027e5f989f6f4e2a0d3fc65a05bdf68169abf8a749d435581ae2497508e6411246c1b0a5f81939418ea7666981f351f0debbbb6ff0062ea3bb19c7d95f38fcaaff84f5cbdf03f8cecb529219a27b7900b881d4ab346dc32907d8f1ef835f44c3e2cd02cafb5bb9b9f145825bbd8a88becdac0b99430321629bc70d82b8519e40af20f8dfac59eb3e35b77b0b8b3bbb686c9235b9b69d65f339627715e0104918fc7bd033ea4b6b886f2d61bab7916482641246ebd1948c823f0af9e7f681f097d8b57b6f13db47886f710dce0702551f29ff8128ffc77debaef807e2dfed5f0d4be1fb993375a6f30e4f2d031e3fef9391f42b5d27c53d4bc309e0ebfd2fc41a8c36ef7311f2231f3cbe60e5195073c301cf4f7a047c83451450014514500145145001451450014514500145145001451450014514500145145002e68cd251400ecd19a6d1400b9a3349450068e8faeea7e1fbe37ba4de4969726368fcc8cf3b58723fcfd6aa5cdccf7970f71753c93cf21dcf248c5998fa9279350d14005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451400514514005145140051451401fffd9),
(3, 'Admin2024', 'admin2024@gmail.com', 'Admin2024', '');

-- --------------------------------------------------------

--
-- Table structure for table `applications`
--

CREATE TABLE `applications` (
  `application_id` int(11) NOT NULL,
  `job_id` int(11) DEFAULT NULL,
  `seeker_id` int(11) DEFAULT NULL,
  `status` enum('pending','approved','rejected') DEFAULT 'pending',
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_post`
--

CREATE TABLE `job_post` (
  `job_id` int(11) NOT NULL,
  `job_title` varchar(255) NOT NULL,
  `job_description` text NOT NULL,
  `recruiter_id` int(11) DEFAULT NULL,
  `location` varchar(100) NOT NULL,
  `salary` int(20) NOT NULL,
  `job_type` varchar(127) NOT NULL,
  `post_on` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `sender_type` varchar(255) DEFAULT NULL,
  `receiver_type` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_recruiter`
--

CREATE TABLE `user_recruiter` (
  `recruiter_id` int(127) NOT NULL,
  `company_name` varchar(127) NOT NULL,
  `detail` text DEFAULT NULL,
  `address` varchar(127) NOT NULL,
  `email` varchar(127) NOT NULL,
  `pnumber` varchar(13) NOT NULL,
  `profile_pic` blob DEFAULT NULL,
  `password` varchar(127) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_recruiter`
--

INSERT INTO `user_recruiter` (`recruiter_id`, `company_name`, `detail`, `address`, `email`, `pnumber`, `profile_pic`, `password`) VALUES
(29, 'Recruiter User', NULL, 'Philippines', 'recruiter@gmail.com', '098765432109', NULL, '12345');

-- --------------------------------------------------------

--
-- Table structure for table `user_seeker`
--

CREATE TABLE `user_seeker` (
  `seeker_id` int(127) NOT NULL,
  `first_name` varchar(127) NOT NULL,
  `last_name` varchar(127) NOT NULL,
  `gender` varchar(127) NOT NULL,
  `month` varchar(127) NOT NULL,
  `day` int(127) NOT NULL,
  `year` int(127) NOT NULL,
  `email` varchar(127) NOT NULL,
  `address` varchar(150) NOT NULL,
  `pnumber` varchar(13) NOT NULL,
  `password` varchar(127) NOT NULL,
  `profile_pic` blob DEFAULT NULL,
  `detail` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_seeker`
--

INSERT INTO `user_seeker` (`seeker_id`, `first_name`, `last_name`, `gender`, `month`, `day`, `year`, `email`, `address`, `pnumber`, `password`, `profile_pic`, `detail`) VALUES
(33, 'Seeker', 'User', 'Male', 'December', 0, 0, '17', 'Philippines', '', '12345', NULL, NULL),
(35, 'Seeker', 'User', 'Male', 'December', 17, 2024, 'seeker@gmail.com', 'Philippines', '09123456789', '12345', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `emaily` (`email`);

--
-- Indexes for table `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`application_id`),
  ADD KEY `applications_ibfk_1` (`job_id`),
  ADD KEY `applications_ibfk_2` (`seeker_id`);

--
-- Indexes for table `job_post`
--
ALTER TABLE `job_post`
  ADD PRIMARY KEY (`job_id`),
  ADD KEY `job_post_ibfk_1` (`recruiter_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_recruiter`
--
ALTER TABLE `user_recruiter`
  ADD PRIMARY KEY (`recruiter_id`),
  ADD UNIQUE KEY `pnumber` (`pnumber`),
  ADD UNIQUE KEY `email` (`email`) USING BTREE;

--
-- Indexes for table `user_seeker`
--
ALTER TABLE `user_seeker`
  ADD PRIMARY KEY (`seeker_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `pnumber` (`pnumber`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(127) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `applications`
--
ALTER TABLE `applications`
  MODIFY `application_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `job_post`
--
ALTER TABLE `job_post`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT for table `user_recruiter`
--
ALTER TABLE `user_recruiter`
  MODIFY `recruiter_id` int(127) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `user_seeker`
--
ALTER TABLE `user_seeker`
  MODIFY `seeker_id` int(127) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `job_post` (`job_id`),
  ADD CONSTRAINT `applications_ibfk_2` FOREIGN KEY (`seeker_id`) REFERENCES `user_seeker` (`seeker_id`);

--
-- Constraints for table `job_post`
--
ALTER TABLE `job_post`
  ADD CONSTRAINT `job_post_ibfk_1` FOREIGN KEY (`recruiter_id`) REFERENCES `user_recruiter` (`recruiter_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
