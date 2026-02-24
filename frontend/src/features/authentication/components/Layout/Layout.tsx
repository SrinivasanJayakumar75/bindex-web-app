import type { ReactNode } from "react";
import classes from "./Layout.module.scss";
export function Layout({children, className}: {children: ReactNode, className: string}
){
    return <div className={`${classes.root} ${className}`}>
        <header className={classes.container}>
            <a href="/">
            <img src="logo.svg" alt="" className={classes.logo}/></a>
        </header>
        <main className={classes.container}>{children}</main>
        <footer>
            <ul className={classes.container}>
                <li>
                    <img src="logo.svg" alt=""/>
                    <span>@2026</span>

                </li>
                <li>
                    <a href="">Accessibility</a>
                </li>
                <li>
                    <a href="">User Agreement</a>
                </li>
                <li>
                    <a href="">Privacy Policy</a>
                </li>
                <li>
                    <a href="">Cookie Policy</a>
                </li>
                <li>
                    <a href="">Copywright policy</a>
                </li>
            </ul>
        </footer>
    </div>
}