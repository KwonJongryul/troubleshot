/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    domains: [
      "localhost",
      "i.namu.wiki/",
      "m.dokidokigoods.co.kr",
      "k9d205-troubleshot.s3.ap-northeast-2.amazonaws.com",
    ],
  },
};

module.exports = nextConfig;
