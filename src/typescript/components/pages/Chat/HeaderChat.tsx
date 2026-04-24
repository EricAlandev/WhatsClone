import Link from "next/link"

type HeaderChatPage = {
    name?: string,

}

export default function HeaderChatPage({name} : HeaderChatPage){

    return(
        <div
            className="flex items-center justify-between h-[10vh] pb-2 pl-2 pr-5 bg-[#161717] border-b-[0.5px] border-[white]"
        >
            <Link
                href={"/homepage"}
            >
                <img
                    src={"/generals/Back.png"}
                    className="min-w-[28px] min-h-[28px] max-w-[40px] max-h-[40px]   mt-3 ml-3 "
                />
            </Link>

            {/* Name + profile picture */}
            <div
                className="flex items-center mt-3"
            >
                <img
                    src={"/configurationsPage/genericProfilePicture.png"}
                />

                <p>
                    {name}
                </p>
            </div>

            {/*Config*/}
            <div>
                {/*3 points */}
                <div
                    className="leading-2 font-bold text-[23px] text-[#F1F1F1]"
                >
                    <p>.</p>
                    <p>.</p>
                    <p>.</p>
                </div>
            </div>
        </div>
    )
}