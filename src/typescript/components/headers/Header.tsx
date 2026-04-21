import Link from "next/link";


export default function Header(){

    return(
        <div
            className="mx-auto mt-5"
        >
            
            <div
                className="flex justify-between "
            >
                <Link
                    href={"/homepage"}
                    className="text-[18px] text-[#f1f1f1]"
                >
                    WhatsApp
                </Link>
                
                {/*3 points */}
                <Link
                    href={"/configurations"}
                    className="leading-2 font-bold text-[20px] text-[#F1F1F1]"
                >
                    <p>.</p>
                    <p>.</p>
                    <p>.</p>
                </Link>
            </div>

            {/*Search Bar */}
            <form
                className="relative mt-3"
            >
                    <img
                        src={"/generals/SearchIcon.png"}
                        className="absolute top-2 left-1.5 max-h-[32px] "
                    />

                    <input

                        placeholder="Search"
                        className=" w-[90vw] min-h-[45px] mx-auto pl-12.5 bg-[#2E2F2F] text-[17px] rounded-md placeholder:text-[#E2E8F0]"
                    />
            </form>
        </div>
    )
}