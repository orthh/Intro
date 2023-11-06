export type SiteConfig = typeof siteConfig;

export const siteConfig = {
  name: 'orthh page',
  description:
    '개인 기록, 포트폴리오 페이지',
  mainNav: [
    {
      title: 'Home',
      href: '/',
    },
    {
      title: 'Board',
      href: '/board'
    },
    {
      title: 'Portfolio',
      href: '/portfolio'
    }
    
  ],
  links: {
    github: 'https://github.com/orthh',
    docs: 'https://platejs.org',
  },
  login: {
    title : '로그인',
    href: '/login'
  }
};
