import "./styles.css";
import {ReactComponent as Logo} from "./assets/logo.svg";
import { Link } from "react-router-dom";

function Navbar() {
    return (
        <nav className="main-navbar">
            <Logo />
            <Link className="logo-text" to="/">DS Delivery</Link>
        </nav>
    );
}

export default Navbar;