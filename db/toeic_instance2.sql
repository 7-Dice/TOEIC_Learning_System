USE toeic_learning_system;

START TRANSACTION;

-- Part 5: 独立した短文穴埋め 30問
INSERT INTO questions
(group_id, sequence_no, part_type, question_text, option_a, option_b, option_c, option_d, correct_answer, explanation)
VALUES
(NULL,NULL,5,'The board will announce its final decision _____ Friday.','at','by','during','among','B','by Friday は「金曜日までに」。'),
(NULL,NULL,5,'Ms. Ortega _____ the revised budget to the finance team yesterday.','send','sent','sending','has send','B','yesterday があるため過去形。'),
(NULL,NULL,5,'The new printer is _____ efficient than the previous model.','much','more','most','many','B','efficient の比較級は more efficient。'),
(NULL,NULL,5,'All visitors must wear identification badges while _____ the facility.','tour','toured','touring','to tour','C','while の後は現在分詞で「施設を見学している間」。'),
(NULL,NULL,5,'Please contact the help desk if you _____ any difficulty accessing the portal.','experience','experiences','experienced','experiencing','A','if節では現在形を使う。'),
(NULL,NULL,5,'The conference room is unavailable _____ it is being renovated.','although','because','unless','therefore','B','改装中であることが理由なので because。'),
(NULL,NULL,5,'Neither the director nor the assistants _____ available for comment.','was','is','were','be','C','norに近い主語 assistants に合わせて were。'),
(NULL,NULL,5,'The company has expanded its services _____ several neighboring cities.','into','onto','beside','across from','A','expand into cities は「都市へ事業を拡大する」。'),
(NULL,NULL,5,'Customers can receive a full refund _____ they return the item within 30 days.','provided that','even though','as soon as','in case','A','provided that は「～という条件で」。'),
(NULL,NULL,5,'The _____ of the new branch is scheduled for early September.','open','opened','opening','opens','C','定冠詞 the の後に名詞 opening が必要。'),
(NULL,NULL,5,'Mr. Kim requested that the report _____ before noon.','is completed','be completed','will complete','completing','B','request that の後は原形。受動態なので be completed。'),
(NULL,NULL,5,'The hotel offers guests complimentary _____ to the fitness center.','access','accesses','accessible','accessing','A','complimentary access は「無料利用」。'),
(NULL,NULL,5,'The shipment arrived _____ than the supplier had promised.','early','earlier','earliest','more early','B','than と対応する比較級 earlier。'),
(NULL,NULL,5,'Please keep a copy of the receipt _____ your records.','for','with','from','under','A','for your records は「記録として」。'),
(NULL,NULL,5,'The training session was postponed _____ the instructor became ill.','due','because','because of','owing','B','後ろに主語＋動詞が続くため because。'),
(NULL,NULL,5,'Each applicant should submit _____ résumé with the online form.','a','an','the','any','A','résumé は子音音で始まるため a。'),
(NULL,NULL,5,'The maintenance team _____ the elevators every six months.','inspect','inspects','inspecting','inspection','B','主語 team を単数扱いし、現在形は inspects。'),
(NULL,NULL,5,'The proposal was approved _____ a few minor changes.','except','despite','although','unless','B','despite の後ろに名詞句が続く。'),
(NULL,NULL,5,'Employees are encouraged to share ideas _____ improving customer service.','for','about','on','by','B','ideas about ～ は「～についてのアイデア」。'),
(NULL,NULL,5,'The museum will remain open _____ the construction work is completed.','until','during','while of','within','A','until は「～するまで」。'),
(NULL,NULL,5,'The new software makes it easier _____ project expenses.','track','tracking','to track','tracked','C','make it easier to do の形。'),
(NULL,NULL,5,'The sales figures for April were _____ than those for March.','high','higher','highest','highly','B','figuresを比較するため higher。'),
(NULL,NULL,5,'Please inform the receptionist _____ you arrive at the office.','when','what','which','whose','A','「到着したとき」は when。'),
(NULL,NULL,5,'The committee will review all applications _____ selecting a candidate.','before','among','beside','toward','A','before selecting は「選考する前に」。'),
(NULL,NULL,5,'This discount is available to members _____ make purchases online.','who','whose','which','whom','A','先行詞 members を主語として受ける関係代名詞 who。'),
(NULL,NULL,5,'The latest catalog contains _____ information about each product.','detail','detailed','detailing','details','B','informationを修飾する形容詞 detailed。'),
(NULL,NULL,5,'The manager asked the staff to respond to all inquiries _____.','prompt','promptly','promptness','more prompt','B','respondを修飾する副詞 promptly。'),
(NULL,NULL,5,'A replacement part will be sent _____ charge.','without','between','against','through','A','without charge は「無料で」。'),
(NULL,NULL,5,'The auditorium can accommodate _____ 500 people.','up to','as many','more than of','much as','A','up to は「最大～まで」。'),
(NULL,NULL,5,'The revised schedule, along with the updated maps, _____ attached to the email.','are','were','is','have','C','主語は単数の schedule。along with は主語の数を変えない。');

-- Part 6: 4本文セット×4問
INSERT INTO passage_groups (part_type, group_type, title) VALUES (6,'SINGLE','Part 6 - Office relocation notice');
SET @g = LAST_INSERT_ID();
INSERT INTO passage_group_items (group_id,sequence_no,speaker,text_body)
VALUES (@g,1,NULL,'Office Relocation Notice

The Customer Support Department will move to the fourth floor on June 12. The move is being made _____ (1) the department has added several new employees. Staff members should pack their personal items by June 10. _____ (2), labeled boxes will be delivered to each desk on Monday. The IT team will disconnect computers after 5:00 P.M. on June 11. Employees may contact Facilities _____ (3) they need help moving heavy equipment. The department expects to resume normal operations on June 13. Thank you for your _____ (4).');

INSERT INTO questions VALUES
(NULL,@g,1,6,'Blank (1)','although','because','unless','while','B','後ろに理由を表す節が続く。'),
(NULL,@g,2,6,'Blank (2)','In addition','For example','Instead of','As a result of','A','前文に続けて追加情報を述べている。'),
(NULL,@g,3,6,'Blank (3)','if','despite','during','so','A','「必要なら連絡する」という条件。'),
(NULL,@g,4,6,'Blank (4)','cooperate','cooperative','cooperation','cooperatively','C','your の後には名詞 cooperation。');

INSERT INTO passage_groups (part_type, group_type, title) VALUES (6,'SINGLE','Part 6 - Product update email');
SET @g = LAST_INSERT_ID();
INSERT INTO passage_group_items (group_id,sequence_no,speaker,text_body)
VALUES (@g,1,'Lena Park','To: Sales Team
Subject: New product guide

The updated product guide is now available on the company portal. It includes the specifications for our spring collection and _____ (1) pricing information. Please replace any printed copies of the old guide with the new version. The marketing department has also prepared a short presentation _____ (2) sales representatives can use during client visits. _____ (3), the presentation is available in both English and French. If you notice an error in the guide, please send it to me _____ (4) Thursday.');

INSERT INTO questions VALUES
(NULL,@g,1,6,'Blank (1)','current','currently','currency','currents','A','pricing information を修飾する形容詞。'),
(NULL,@g,2,6,'Blank (2)','who','that','where','whose','B','presentationを先行詞とする関係代名詞。'),
(NULL,@g,3,6,'Blank (3)','For instance','Nevertheless','In contrast','Otherwise','A','利用可能な言語の例・具体情報を追加している。'),
(NULL,@g,4,6,'Blank (4)','by','among','since','over','A','by Thursday は「木曜日までに」。');

INSERT INTO passage_groups (part_type, group_type, title) VALUES (6,'SINGLE','Part 6 - Community center announcement');
SET @g = LAST_INSERT_ID();
INSERT INTO passage_group_items (group_id,sequence_no,speaker,text_body)
VALUES (@g,1,NULL,'Community Center Announcement

The Westbrook Community Center will offer free computer classes beginning next month. Classes will meet every Tuesday evening in Room 204. Participants should bring a laptop _____ (1) they have one; several computers will also be available to borrow. Each class is limited to 15 people, so advance registration is _____ (2). Registration forms can be downloaded from our website or collected at the front desk. The first class _____ (3) basic online safety. To reserve a place, call the center _____ (4) 555-0184.');

INSERT INTO questions VALUES
(NULL,@g,1,6,'Blank (1)','if','because','until','unless','A','ノートパソコンを持っている場合という条件。'),
(NULL,@g,2,6,'Blank (2)','require','required','requiring','requirement','B','is required の受動態。'),
(NULL,@g,3,6,'Blank (3)','cover','covers','covered','covering','B','主語 The first class に対応する現在形。'),
(NULL,@g,4,6,'Blank (4)','at','in','on','by','A','電話番号には call ... at ... を使う。');

INSERT INTO passage_groups (part_type, group_type, title) VALUES (6,'SINGLE','Part 6 - Shipping policy update');
SET @g = LAST_INSERT_ID();
INSERT INTO passage_group_items (group_id,sequence_no,speaker,text_body)
VALUES (@g,1,'Northline Supplies','Shipping Policy Update

Starting August 1, orders over $75 will qualify for free standard shipping. Orders _____ (1) this amount will be charged a flat fee of $6. Express delivery remains available for an additional charge. Customers can track shipments through the link _____ (2) in their confirmation email. Please note that delivery dates are estimates and may change _____ (3) severe weather. We have updated the policy page to _____ (4) these changes.');

INSERT INTO questions VALUES
(NULL,@g,1,6,'Blank (1)','below','beyond','among','within','A','この金額を下回る注文という意味。'),
(NULL,@g,2,6,'Blank (2)','include','included','including','includes','B','link included in ... は過去分詞による後置修飾。'),
(NULL,@g,3,6,'Blank (3)','because','because of','although','therefore','B','後ろに名詞句 severe weather が続く。'),
(NULL,@g,4,6,'Blank (4)','reflect','reflection','reflective','reflects','A','to の後に動詞原形。');

-- Part 7: 単一文書セット 6×3問 = 18問
INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'SINGLE','Part 7 - Library hours');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES (NULL,@g,1,NULL,'RIVERSIDE PUBLIC LIBRARY
Beginning October 1, the library will open at 9:00 A.M. on weekdays, one hour earlier than before. Saturday hours will remain 10:00 A.M. to 4:00 P.M. The second-floor study area will be closed October 5–7 for new lighting installation. Books may still be returned using the outdoor return slot.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'What change will take effect on October 1?','Weekday opening time will be earlier.','Saturday hours will be extended.','The return slot will be removed.','The library will close on weekdays.','A','平日の開館時刻が1時間早くなる。'),
(NULL,@g,2,7,'Which area will be temporarily unavailable?','The entrance lobby','The second-floor study area','The book return area','The children’s room','B','告知に明記されている。'),
(NULL,@g,3,7,'How can patrons return books during the closure?','By mailing them','At the front desk only','Using the outdoor return slot','At the study area','C','屋外返却口は利用できる。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'SINGLE','Part 7 - Workshop advertisement');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES (NULL,@g,1,NULL,'CAREER SKILLS WORKSHOP
Write Clearer Business Emails
Saturday, May 18, 9:30 A.M.–12:00 P.M.
Location: Harbor Training Center, Room 3
Instructor: Dana Cole, communications consultant
Fee: $25; includes course materials
Register by May 14 at harbortraining.example. Participants should bring a laptop. The workshop is limited to 20 attendees.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'Who is the instructor?','A training center director','A communications consultant','A software developer','A career counselor','B','広告に肩書が記載されている。'),
(NULL,@g,2,7,'What is included in the fee?','Lunch','A laptop','Course materials','Parking','C','参加費に教材が含まれる。'),
(NULL,@g,3,7,'What should participants bring?','A printed résumé','A laptop','A registration receipt','A notebook computer charger only','B','広告の最後に記載。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'SINGLE','Part 7 - Store return policy');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES (NULL,@g,1,NULL,'NORTH POINT HOME
Return Policy
Unused items may be returned within 30 days of purchase with the original receipt. Furniture must be returned in its original packaging. Custom-made products and clearance items marked FINAL SALE cannot be returned. Refunds are issued to the original payment method. Online purchases may be returned at any North Point Home location.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'What is required for a return?','A delivery appointment','The original receipt','A membership card','A product warranty','B','返品時にはレシートが必要。'),
(NULL,@g,2,7,'Which item cannot be returned?','An unused lamp','A standard chair','A custom-made table','A boxed shelf','C','特注品は返品不可。'),
(NULL,@g,3,7,'Where may an online purchase be returned?','Only by mail','At any store location','At the warehouse only','At the original payment office','B','オンライン購入品はどの店舗でも返品可能。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'SINGLE','Part 7 - Staff memo');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES (NULL,@g,1,'Mina Shah','To: All Staff
Subject: Visitor parking

Beginning Monday, visitor parking spaces will be located beside the east entrance. Please direct guests to that area rather than the spaces near the loading dock. Employees should continue using the garage on Pine Street. New signs will be installed Friday afternoon. If a visitor requires accessible parking, contact Security before the guest arrives.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'Where should employees direct visitors?','To the Pine Street garage','To the east entrance parking area','To the loading dock','To the west entrance','B','来訪者用の場所は東口の隣。'),
(NULL,@g,2,7,'When will the new signs be installed?','Monday morning','Friday afternoon','Saturday evening','Next month','B','メモに記載。'),
(NULL,@g,3,7,'What should staff do for a visitor needing accessible parking?','Contact Security in advance','Use the loading dock spaces','Ask the visitor to park on Pine Street','Move a company vehicle','A','来訪前にSecurityへ連絡する。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'SINGLE','Part 7 - Museum event');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES (NULL,@g,1,NULL,'CITY MUSEUM
Evening Lecture: Mapping the Coast
Thursday, November 7, 6:30 P.M.
Dr. Elena Ruiz will discuss how historical maps help researchers understand changes to the coastline. Museum members enter free; nonmembers pay $8. Doors open at 6:00 P.M. Seating is limited, and reservations can be made through the museum website.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'What is the lecture about?','Modern ship design','Historical maps and coastal changes','Museum architecture','Marine animal research','B','講演のテーマとして記載されている。'),
(NULL,@g,2,7,'Who can attend without paying?','All visitors','Museum members','Students only','Researchers only','B','会員は無料。'),
(NULL,@g,3,7,'How can a visitor reserve a seat?','By calling Dr. Ruiz','At the museum gift shop','Through the museum website','By sending a letter','C','予約方法はウェブサイト。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'SINGLE','Part 7 - Maintenance notice');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES (NULL,@g,1,NULL,'BUILDING NOTICE
The water supply on floors 6 through 8 will be interrupted on Wednesday, March 20, from 7:00 A.M. to 9:00 A.M. while valves are replaced. Restrooms on floors 1 through 5 will remain open. Building management recommends filling drinking-water containers before Tuesday evening. For urgent assistance, call the facilities desk.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'What will happen on March 20?','Elevators will be replaced.','Water service will be interrupted on some floors.','The building will open late.','Restrooms on every floor will close.','B','6～8階で断水する。'),
(NULL,@g,2,7,'Which facilities will remain available?','Restrooms on floors 1 through 5','Water fountains on floors 6 through 8','The facilities desk','All upper-floor kitchens','A','1～5階のトイレは利用できる。'),
(NULL,@g,3,7,'What does management recommend?','Avoid the building on Tuesday.','Fill water containers in advance.','Use the elevators after 9:00 A.M.','Contact the valve supplier.','B','事前に水を用意するよう案内している。');

-- Part 7: 二重文書セット 4×5問 = 20問
INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'DOUBLE','Part 7 - Delayed office furniture order');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES
(NULL,@g,1,'Email from Riley Chen','To: Oakline Furniture
Subject: Order 7814

Our order of twelve conference chairs was scheduled to arrive on April 3, but it has not arrived. We need the chairs before our client meeting on April 8. Please confirm the current delivery date.'),
(NULL,@g,2,'Reply from Oakline Furniture','Dear Ms. Chen,
We apologize for the delay. The chairs left our warehouse this morning and are expected to arrive on April 5. The delivery company will call your office one day beforehand. We have waived the delivery fee. Please contact us if the chairs have not arrived by April 6.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'Why did Riley Chen contact Oakline Furniture?','To change the chair color','To ask about a late order','To cancel a meeting','To request a price list','B','予定日に届いていない注文を問い合わせた。'),
(NULL,@g,2,7,'When are the chairs now expected to arrive?','April 3','April 5','April 6','April 8','B','返信に記載。'),
(NULL,@g,3,7,'What will the delivery company do?','Call one day before delivery','Collect a new fee','Install the chairs','Reschedule the client meeting','A','事前に電話する。'),
(NULL,@g,4,7,'What has Oakline Furniture done for the customer?','Sent extra chairs','Waived the delivery fee','Refunded the order','Extended the warranty','B','配送料を免除している。'),
(NULL,@g,5,7,'When should Ms. Chen contact Oakline again?','If the chairs are not there by April 6','If the meeting is moved','Before April 3','After the chairs are installed','A','4月6日までに届かなければ連絡する。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'DOUBLE','Part 7 - Cooking class registration');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES
(NULL,@g,1,NULL,'LAKESIDE COOKING SCHOOL
Mediterranean Cooking, June 12, 6:00–8:30 P.M.
Fee: $48, ingredients included. Bring an apron. Register online by June 8. Classes with fewer than six students may be canceled.'),
(NULL,@g,2,'Email from Nia Patel','Hello,
I registered for the June 12 class, but I will be traveling that week. Is it possible to move my registration to the July session? I have already paid the fee.
Nia');
INSERT INTO questions VALUES
(NULL,@g,1,7,'What is included in the class fee?','A cookbook','Ingredients','An apron','Transportation','B','料金には材料費が含まれる。'),
(NULL,@g,2,7,'What must students bring?','An apron','A chef’s knife','A printed recipe','A food container','A','案内に持参物として記載。'),
(NULL,@g,3,7,'Why did Nia Patel write the email?','To ask to change her class date','To request a refund for ingredients','To register another student','To ask about the class location','A','旅行のため7月のクラスへ変更したい。'),
(NULL,@g,4,7,'By what date should students register?','June 6','June 8','June 12','July 8','B','案内の締切日。'),
(NULL,@g,5,7,'What can be inferred about Nia?','She has not paid.','She cannot attend the June session.','She is teaching the class.','She wants to cancel the school.','B','その週は旅行すると述べている。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'DOUBLE','Part 7 - Greenway volunteer day');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES
(NULL,@g,1,NULL,'GREENWAY PARK VOLUNTEER DAY
Saturday, September 14, 8:30 A.M.–12:00 P.M.
Volunteers will plant native flowers and remove litter along the north trail. Gloves and tools will be provided. Wear closed-toe shoes. Meet at the visitor center. Register by September 10.'),
(NULL,@g,2,'Email from Omar Lewis','Hi,
I would like to volunteer on September 14. I can arrive at 9:30 because I have a morning appointment. Is there still work available then?
Omar');
INSERT INTO questions VALUES
(NULL,@g,1,7,'What will volunteers do?','Repair the visitor center','Plant flowers and collect litter','Paint trail signs','Build a new parking area','B','イベントの作業内容。'),
(NULL,@g,2,7,'What should volunteers wear?','Sandals','Closed-toe shoes','A park uniform','A raincoat','B','安全のため閉じた靴を着用する。'),
(NULL,@g,3,7,'Why did Omar Lewis write?','To ask whether he can arrive late','To borrow gardening tools','To cancel his registration','To change the event date','A','9:30到着でも作業があるか尋ねている。'),
(NULL,@g,4,7,'Where should volunteers meet?','At the north trail entrance','At the visitor center','At the parking office','At the flower shop','B','案内に記載。'),
(NULL,@g,5,7,'When is the registration deadline?','September 10','September 14','September 15','September 30','A','案内の締切日。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'DOUBLE','Part 7 - Hotel booking confirmation');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES
(NULL,@g,1,'Hotel confirmation','SEABROOK HOTEL
Reservation 62048
Guest: Marcus Bell
Check-in: October 21; check-out: October 24
One king room, breakfast included. Check-in begins at 3:00 P.M. Parking is complimentary for registered guests.'),
(NULL,@g,2,'Email from Marcus Bell','Dear Seabrook Hotel,
My train is scheduled to arrive at 1:15 P.M. Could I leave my suitcase at the hotel before the room is ready? Also, please note that I will need a vegetarian breakfast.
Thank you,
Marcus Bell');
INSERT INTO questions VALUES
(NULL,@g,1,7,'How long is Marcus Bell’s stay?','Two nights','Three nights','Four nights','Five nights','B','10月21日から24日までの3泊。'),
(NULL,@g,2,7,'What is included with the reservation?','Airport transport','Breakfast','Laundry service','A late checkout','B','予約情報に朝食付きとある。'),
(NULL,@g,3,7,'What does Marcus ask about?','Leaving his luggage before check-in','Changing his room type','Extending his stay','Receiving a parking discount','A','部屋の準備前に荷物を預けたい。'),
(NULL,@g,4,7,'What dietary request does Marcus make?','A gluten-free dinner','A vegetarian breakfast','A dairy-free lunch','A low-sodium meal','B','メールで朝食の希望を伝えている。'),
(NULL,@g,5,7,'When can guests begin checking in?','1:15 P.M.','2:00 P.M.','3:00 P.M.','4:00 P.M.','C','確認書に記載。');

-- Part 7: 三重文書セット 2×8問 = 16問
INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'TRIPLE','Part 7 - Regional sales meeting');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES
(NULL,@g,1,'Email from Dana','To: Regional Sales Team
Subject: Quarterly meeting

Our quarterly meeting will be held at the Brookside Hotel on May 22, rather than at headquarters. Please arrive by 9:15 A.M. The first session begins at 9:30. A shuttle will leave headquarters at 8:30 for anyone who needs transportation.'),
(NULL,@g,2,NULL,'BROOKSIDE HOTEL
Meeting rooms are on the second floor. The hotel café opens at 7:00 A.M. Lunch will be served in the Garden Room at 12:30 P.M. Guests arriving by car may use the east parking lot; parking validation is available at the registration desk.'),
(NULL,@g,3,'Reply from Luis','Hi Dana,
I will drive directly to the hotel. I have a client call at 8:45, so I may arrive shortly after 9:15. I will pick up my meeting materials at the registration desk when I get there.
Luis');
INSERT INTO questions VALUES
(NULL,@g,1,7,'Where will the meeting take place?','At headquarters','At the Brookside Hotel','At the regional office','At the Garden Room only','B','案内メールに会場変更が記載。'),
(NULL,@g,2,7,'When does the first session begin?','8:30 A.M.','9:15 A.M.','9:30 A.M.','12:30 P.M.','C','メールに記載。'),
(NULL,@g,3,7,'What is available to people who drive to the hotel?','Free shuttle service','Parking validation','A breakfast voucher','Reserved rooms','B','ホテル案内に記載。'),
(NULL,@g,4,7,'Why will Luis arrive after 9:15?','He has a client call.','He is taking the shuttle.','He is checking into the hotel.','He is serving lunch.','A','返信に理由が書かれている。'),
(NULL,@g,5,7,'Where will lunch be served?','At headquarters','In the Garden Room','At the registration desk','In the hotel café','B','ホテル案内に記載。'),
(NULL,@g,6,7,'How will Luis get to the hotel?','By shuttle','By train','By car','With Dana','C','返信で車で直接向かうと述べている。'),
(NULL,@g,7,7,'What will Luis do upon arrival?','Call the client','Pick up meeting materials','Validate his hotel room','Order lunch','B','登録デスクで資料を受け取る。'),
(NULL,@g,8,7,'What can be inferred about Luis?','He plans to attend the meeting.','He will miss the entire meeting.','He is arranging the shuttle.','He works at the hotel.','A','到着後に資料を受け取る予定と述べている。');

INSERT INTO passage_groups (part_type,group_type,title) VALUES (7,'TRIPLE','Part 7 - Equipment service appointment');
SET @g=LAST_INSERT_ID();
INSERT INTO passage_group_items VALUES
(NULL,@g,1,'Email from Priya','To: Metro Office Services
Subject: Copier service

The copier in our West Avenue office has been producing faded pages. Could a technician visit this week? We are available Thursday morning. The machine’s service label lists asset number W-204.'),
(NULL,@g,2,NULL,'METRO OFFICE SERVICES
Service appointments are available Wednesday, June 5, 1:00–4:00 P.M., and Thursday, June 6, 9:00 A.M.–noon. Please provide the asset number when booking. A technician will inspect the machine and replace standard parts if needed.'),
(NULL,@g,3,'Reply from Metro Office Services','Dear Ms. Nair,
We have scheduled a technician for Thursday, June 6, between 10:00 and 11:00 A.M. The visit is covered by your maintenance plan. Please ensure that someone can provide access to the copier. The technician will call when en route.');
INSERT INTO questions VALUES
(NULL,@g,1,7,'What problem has Priya reported?','The copier produces faded pages.','The office has run out of paper.','The copier will not turn on.','The service label is missing.','A','Priyaのメールに記載。'),
(NULL,@g,2,7,'When is Metro Office Services available on Thursday?','8:00–9:00 A.M.','9:00 A.M.–noon','Noon–3:00 P.M.','1:00–4:00 P.M.','B','サービス案内に記載。'),
(NULL,@g,3,7,'What information does the service company request?','The copier’s purchase price','The asset number','The office manager’s address','A list of employees','B','予約時にasset numberを提供する。'),
(NULL,@g,4,7,'When is the technician scheduled to arrive?','Wednesday afternoon','Thursday, 9:00–10:00 A.M.','Thursday, 10:00–11:00 A.M.','Thursday afternoon','C','返信メールに記載。'),
(NULL,@g,5,7,'How will the visit be paid for?','By credit card','By the maintenance plan','By the office landlord','It will be billed next year.','B','保守契約でカバーされる。'),
(NULL,@g,6,7,'What should the office ensure?','Someone can give the technician access.','The copier is moved outside.','All standard parts are ordered.','The service label is replaced.','A','返信でアクセスできる人を用意するよう求めている。'),
(NULL,@g,7,7,'What will the technician do while traveling to the office?','Send an invoice','Call the office','Order a new copier','Contact the manufacturer','B','到着に向かう際に電話する。'),
(NULL,@g,8,7,'Which day did Priya originally say was convenient?','Wednesday','Thursday','Friday','Saturday','B','Priyaは木曜午前が都合よいと伝えている。');

-- 登録件数の確認
SELECT part_type, COUNT(*) AS question_count
FROM questions
GROUP BY part_type
ORDER BY part_type;

SELECT g.group_type, g.title, COUNT(q.question_id) AS question_count
FROM passage_groups g
LEFT JOIN questions q ON q.group_id = g.group_id
WHERE g.part_type IN (6,7)
GROUP BY g.group_id, g.group_type, g.title
ORDER BY g.part_type, g.group_id;

COMMIT;