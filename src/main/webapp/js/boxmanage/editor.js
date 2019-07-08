define('editor',["avalon"], function(avalon) {
	var currentRange,_parentElem,supportRange = typeof document.createRange === 'function';
	function getCurrentRange() {
		var selection,
		range,
		txt = $('#editor');
		if(supportRange){
            selection = document.getSelection();
            if (selection.getRangeAt && selection.rangeCount) {
            	range = document.getSelection().getRangeAt(0);
            	_parentElem = range.commonAncestorContainer;
            }
        }else{
            range = document.selection.createRange();
            _parentElem = range.parentElement();
        }
        if( _parentElem && (avalon.contains(txt, _parentElem) || txt === _parentElem) ){
        	parentElem = _parentElem;
        	return range;
        }
        return range;
    }
    function saveSelection() {
    	currentRange = getCurrentRange();
    }
    function restoreSelection() {
    	if(!currentRange){
    		return;
    	}
    	var selection,
    	range;
    	if(supportRange){
    		selection = document.getSelection();
    		selection.removeAllRanges();
    		selection.addRange(currentRange);
    	}else{
            range = document.selection.createRange();
            range.setEndPoint('EndToEnd', currentRange);
            if(currentRange.text.length === 0){
            	range.collapse(false);
            }else{
            	range.setEndPoint('StartToStart', currentRange);
            }
            range.select();
        }
    }
    function insertImage(html){
    	console.log("insertImage");
    	restoreSelection();
    	if(document.selection)
    		currentRange.pasteHTML(html); 
    	else
			document.execCommand("insertImage", false,html);
    	saveSelection();
    }
    avalon.bind($('#editor'),'mouseup',function(e){
    	saveSelection();
    });
    avalon.bind($('#editor'),'keyup',function(e){
    	saveSelection();
    });
    return {insertImage:insertImage};
});