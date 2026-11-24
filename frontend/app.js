const form = document.querySelector('#form');
const title = document.querySelector('#title');
const content = document.querySelector('#content');
const message = document.querySelector('#message');
const posts = document.querySelector('#posts');
const cancel = document.querySelector('#cancel');
let editingId = null;

async function request(path, options) {
  const response = await fetch('/v1/posts' + path, options);
  const result = await response.json();
  if (!response.ok) throw new Error(result.message || '요청에 실패했습니다.');
  return result.data;
}

function resetForm() { editingId = null; form.reset(); cancel.hidden = true; }
async function loadPosts() {
  const items = await request('');
  posts.replaceChildren();
  if (!items.length) { posts.textContent = '등록된 게시글이 없습니다.'; return; }
  for (const post of items) {
    const article = document.createElement('article');
    const heading = document.createElement('h3'); heading.textContent = post.title;
    const body = document.createElement('p'); body.textContent = post.content;
    const date = document.createElement('p'); date.textContent = `작성: ${post.createdAt.replace('T', ' ').slice(0, 19)}`;
    const edit = document.createElement('button'); edit.textContent = '수정';
    edit.onclick = () => { editingId = post.id; title.value = post.title; content.value = post.content; cancel.hidden = false; title.focus(); };
    const remove = document.createElement('button'); remove.textContent = '삭제';
    remove.onclick = async () => {
      if (!confirm('이 게시글을 삭제할까요?')) return;
      try { await request('/' + post.id, { method: 'DELETE' }); if (editingId === post.id) resetForm(); message.textContent = ''; await loadPosts(); }
      catch (error) { message.textContent = error.message; }
    };
    article.append(heading, body, date, edit, remove); posts.append(article);
  }
}
form.addEventListener('submit', async event => {
  event.preventDefault();
  if (!title.value.trim() || !content.value.trim()) { message.textContent = '제목과 내용을 입력해 주세요.'; return; }
  try {
    await request(editingId === null ? '' : '/' + editingId, {
      method: editingId === null ? 'POST' : 'PUT', headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title: title.value.trim(), content: content.value.trim() })
    });
    resetForm(); message.textContent = ''; await loadPosts();
  } catch (error) { message.textContent = error.message; }
});
cancel.addEventListener('click', resetForm);
loadPosts().catch(error => { message.textContent = error.message; });
