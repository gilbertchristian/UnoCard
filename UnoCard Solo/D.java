System.out.println("Declare HIJI? [Please enter value 1 or 2]");
            System.out.println("1. Yes");
            System.out.println("2. No");
            long then = System.currentTimeMillis();
            int declare = input.nextInt();
            long now = System.currentTimeMillis();
            double waktu = ((now - then) / 1000d);
            if ( declare == 1){
                if (isOneCardLeft(currentPlayer)){
                    if (waktu > 3d){
                        System.out.println("You took " + waktu + " seconds to declare HIJI.");
                        System.out.println("Declare hiji failed.");
                        drawTwo(0);
                    }
                    else{
                        System.out.println("You took " + waktu + " seconds to declare HIJI.");
                        System.out.println("Declare HIJI accomplished");
                    }
                }
                else{
                    System.out.println("You are not supposed to declare HIJI");
                    drawTwo(0);
                }
            }
            else if (declare == 2){
                if (isOneCardLeft(currentPlayer)){
                    System.out.println("You have one card left and are supposed to declare HIJI");
                    drawTwo(0);
                } 
            }