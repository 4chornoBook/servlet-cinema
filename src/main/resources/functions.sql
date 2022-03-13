-- this function checks time slow passed through parameters and return boolean value
-- true - if time slot is available
-- false -if unavailable
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
