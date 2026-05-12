
type DefineHowManyFixed = {
    quantity: number
}

export default function DefineHowManyFixed({quantity}: DefineHowManyFixed){

    //define the quantity of fixed messages;
    const fixedMessages : string[] = [];

    for(let i = 0; i < quantity; i++){
        fixedMessages.push("value");
    }

    //prop who define the height of each fixed message simbol;
    let height = "h-[10px]";

    switch(quantity){
        case 1:
         height = "h-[10px]"
         break;

        case 2:
         height = "h-[5px]"
         break;

        case 3:
         height = "h-[3px]"
         break;

        default:
         height = "h-[10px]"
         break
    }

    return(
        <>  
            {fixedMessages.length > 0 && (
                <div
                    className="gap-2 ml-2 mb-5"
                >
                    {fixedMessages.map(() => {
                        return(
                            <div>
                                <p className={`${height} text-[white]`}>|</p>
                            </div>
                        )
                        })
                    }
                </div>
            )}
        </>
    )
}