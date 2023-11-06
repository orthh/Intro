import Link from 'next/link';

import { siteConfig } from '@/config/site';
import { Icons } from '@/components/icons';
import { buttonVariants } from '@/components/plate-ui/button';
import { MainNav } from '@/components/site/main-nav';
import { ThemeToggle } from '@/components/site/theme-toggle';

export function SiteHeader() {
  return (
    <header className="sticky top-0 z-40 w-full border-b bg-background">
      <div className="container flex h-16 items-center space-x-4 sm:justify-between sm:space-x-0">
        <MainNav items={siteConfig.mainNav} />
        <div className="flex flex-1 items-center justify-end space-x-4">
          <nav className="flex items-center space-x-1">
            <Link
              href={siteConfig.links.github}
              target="_blank"
              rel="noreferrer"
            >
              <div
                className={buttonVariants({
                  size: 'sm',
                  variant: 'ghost',
                })}
              >
                <Icons.gitHub className="h-5 w-5" />
                <span className="sr-only">GitHub</span>
              </div>
            </Link>
            <ThemeToggle />
            <Link
                href={siteConfig.login.href}
                className={
                  'flex items-center text-sm font-medium text-muted-foreground'
                }
              >
                {siteConfig.login.title}
            </Link>
          </nav>
        </div>
      </div>
    </header>
  );
}
