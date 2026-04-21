import Link from "next/link";


type HeaderConfig = {
    name?: string
}

export default function HeaderConfig( {name} : HeaderConfig){

    return(
        <div
            className=" w-[90vw] mx-auto mt-5"
        >   
            {/*Back and SearchIcon */}
            <div
                className="flex items-center justify-between"
            >
                <Link
                    href={"/homepage"}
                >
                    <img
                        src={"/generals/Back.png"}
                        className="w-[32px] h-[32px]"
                    />
                </Link>

                <img
                    src={"/generals/SearchIcon.png"}
                    className="w-[28px] h-[28px]"
                />
            </div>

            {/*Profile picture + name */}

            <div
                className="flex flex-col items-center mt-5 gap-2"
            >
                <img
                    src={"/configurationsPage/genericProfilePicture.png"}
                    className="w-[18vw]"
                />

                <p
                    className="text-[18px] text-[white] text-center"
                >
                    {name}
                </p>
            </div>


        </div>
    )
}