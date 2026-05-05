
type HeaderSelectedChat = {
    hiddenOrNot?: string;
    quantityMessages?: number[];
    back: () => void;
    edit: () => void;
    putFixado: () => void;
    deleteMessages: (value: void) => void;
}

export default function HeaderSelectedChat({hiddenOrNot, quantityMessages, back, edit, putFixado, deleteMessages, } : HeaderSelectedChat){

    const moreThanOneMessage : boolean = (quantityMessages?.length > 1) ? true : false

    return(
        <div
            className={`flex items-center justify-between h-[10vh] pb-2 pl-2 pr-5 bg-[#161717] border-b-[0.5px] border-[white] ${hiddenOrNot}`}
        >

            <img
                src={"/generals/Back.png"}
                onClick={() => {
                    back();
                }}
                className="min-w-[28px] min-h-[28px] max-w-[40px] max-h-[40px] mt-3 ml-3 "
            />


            <div
                className="flex"
            >
                <img
                    src={"/messages/trash.png"}
                    onClick={() => {
                        deleteMessages()
                    }}
                    className="min-w-[28px] min-h-[28px] max-w-[25px] max-h-[25px] mt-3 ml-3 "
                />

                {moreThanOneMessage === false && (
                    <>
                        <img
                            src={"/messages/change.png"}
                            onClick={() => {
                                edit()
                            }}
                            className="min-w-[28px] min-h-[28px] max-w-[25px] max-h-[25px] mt-3 ml-3 "
                        />

                        <img
                            src={"/messages/PutFixado.png"}
                            onClick={() => {
                                putFixado()
                            }}
                            className="min-w-[28px] min-h-[28px] max-w-[25px] max-h-[25px] mt-3 ml-3 "
                        />
                    </>
                )}
            </div>
        </div>
    )
}