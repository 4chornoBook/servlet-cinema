-- drop function is_slot_available;

create function is_slot_available(session_day date, beginning time, ending time) returns boolean as $$
	select case
	when exists(
		select session_id from movie_session
		where session_date = session_day
		and (
			(ending <= ending_time and ending > beginning_time )
			or
			(beginning < ending_time and beginning >= beginning_time)
		)
	) then false else true
	end
$$ language sql;




-- create function is_slot_available(session_day date, beginning time, ending time) returns boolean as $$
-- 	select case
-- when exists (select session_id from movie_session
-- where session_date = session_day
-- 	and (beginning_time > beginning and beginning_time < ending 
-- 	or ending_time > beginning and ending_time < ending)
-- 	and beginning_time != ending
-- 	and ending_time != beginning) then false else true 
-- 	end
-- $$ language sql;
