var $ = function(e) {
	return document.getElementById(e)
};
document.execCommand("BackgroundImageCache", !1, !0),
	function(e, t) {
		"use strict";
		var n = e.document,
			r = !!n.addEventListener,
			i, s = /^(http(?:s)?\:\/\/|file\:.+\:\/)/,
			o = /([^\/?]+?)(\.(?:js|css))?(\?.*)?$/,
			u = /loaded|complete|undefined/,
			a = {},
			f = {},
			l = {
				baseUrl: null,
				charset: {}
			},
			c = n.head || n.getElementsByTagName("head")[0] || n.documentElement,
			h = {};
		h.mix = function(e, n, r, i) {
			if (!e || !n) return;
			r === t && (r = !0);
			var s, o, u, a = function(t) {
				if (r === !0 || !(t in e)) e[t] = n[t]
			};
			if (i && (o = i.length))
				for (u = o; u;) s = i[--u], s in n && a(s);
			else
				for (s in n) a(s);
			return e
		}, h.mix(h, {
			__uuid__: 2,
			module: {},
			merge: function() {
				var e = {};
				for (var t = 0, n = arguments.length; t < n; t++) h.mix(e, arguments[t]);
				return e
			},
			guid: function(e) {
				return (e || "seed_") + +(new Date) + (Math.random() + "").slice(-8)
			},
			modify: function(e, t) {
				f[e] = t
			},
			config: function(e) {
				var t = e.baseUrl,
					r = t.slice(0, 4) === "http";
				r ? l.baseUrl = t : l.baseUrl = p.mergePath(t, n.location.href), l.charset = h.merge(l.charset, e.charset)
			},
			error: function(e) {
				throw new Error(e)
			}
		}), h.euid = h.guid(), i = h.guid("easyJS_mod_");
		var p = {
			init: function() {
				var e = 0,
					t, r, i, s;
				n.currentScript ? t = n.currentScript : (r = n.getElementsByTagName("script"), t = r[r.length - 1]), i = t.getAttribute("data-main"), s = t.hasAttribute ? t.src : t.getAttribute("src", 4), l.baseUrl = s.slice(0, s.lastIndexOf("/") + 1), i && (i = i.split(","), h.use(i)), r = t = null
			},
			getCurrentScript: function() {
				var e, t, n, r;
				try {
					h[h.euid]()
				} catch (s) {
					r = s.stack
				}
				if (r) return r = r.split(/[@ ]/g).pop(), r = r[0] === "(" ? r.slice(1, -1) : r.replace(/\s/, ""), r.replace(/(:\d+)?:\d+$/i, "").match(o)[1];
				t = c.getElementsByTagName("script"), n = t.length - 1;
				for (; n >= 0; n--) {
					e = t[n];
					if (e.className === i && e.readyState === "interactive") break
				}
				return e.src.match(o)[1]
			},
			mergePath: function(e, t) {
				var n = t.slice(0, 4) === "http",
					r = "",
					i = 0,
					o, n, u, a, f, l, c;
				o = t.match(s)[1], t = t.slice(o.length), n && (r = t.slice(0, t.indexOf("/") + 1), t = t.slice(r.length)), u = t.split("/"), u.pop(), a = e.split("/"), a.pop(), l = a.length;
				for (; i < l; i++) c = a[i], c === ".." ? u.pop() : c !== "." && u.push(c);
				return f = u.join("/"), f = f === "" ? "" : f + "/", o + r + f
			},
			parseModId: function(e, t) {
				var n = s.test(e),
					r = e.match(o),
					i = r[1],
					u = r[2] || ".js",
					a = r[3] || "",
					f, l;
				return n && (t = e, e = ""), f = p.mergePath(e, t), l = f + i + u + a, [i, l]
			},
			getExports: function(e) {
				if (e) {
					var t = e.length,
						n = h.module,
						r = [],
						i = 0,
						s = 0,
						o;
					for (; i < t; i++) r[s++] = n[e[i]].exports;
					return r
				}
				return []
			},
			isLoaded: function(e) {
				var t = e.deps,
					n = t.length,
					r = h.module,
					i = 0,
					s;
				for (; i < n; i++) {
					s = r[t[i]];
					if (s.status !== 4) return !1
				}
				return !0
			},
			factoryHandle: function(e, n, r, i) {
				n.status = 3;
				var s = p.getExports(n.deps),
					o = typeof r == "function" ? r.apply(null, s) : r;
				o !== t && (f[e] && (o = f[e](o), delete f[e]), n.exports = o), n.status = 4, i && i.length--
			},
			fireFactory: function(e) {
				var t = a[e],
					n = t.factorys,
					r = n[0],
					i, s, o, u;
				if (!r) return;
				o = r.name, u = h.module[o], p.isLoaded(u) && (n.shift(), p.factoryHandle(o, u, r.factory, t), n.length && p.fireFactory(e))
			},
			complete: function(e) {
				var t = h.module,
					n = e.useKey,
					r = a[n],
					i = 0,
					s = 0,
					o, u, f, l, c;
				delete e.useKey;
				if (!r) return;
				if (r.urls.length) p.load(n);
				else if (!r.length) {
					o = r.namesCache, f = o.length, u = [];
					for (; i < f; i++) c = o[i], l = t[c], l.status !== 4 && h.error("[" + c + "] module failed to use."), u[s++] = l.exports;
					r.callback && r.callback.apply(null, u), delete a[n]
				}
			},
			create: function(e, t, s) {
				var o = l.charset[t],
					f = h.module[t],
					d, v;
				return f.useKey = s, f.status = 1, ~e.indexOf(".css") ? (v = n.createElement("link"), v.rel = "stylesheet", v.href = e, o && (v.charset = o), v.onload = v.onerror = function() {
					v = v.onload = v.onerror = null, f.status = 4, a[s].length--, p.fireFactory(s), p.complete(f)
				}, v) : (d = n.createElement("script"), d.className = i, d.async = !0, o && (d.charset = o), r && (d.onerror = function() {
					d.onerror = d.onload = null, c.removeChild(d), d = null, h.error("[" + t + "] module failed to load, the url is " + e + ".")
				}), d[r ? "onload" : "onreadystatechange"] = function() {
					if (r || u.test(d.readyState)) d[r ? "onload" : "onreadystatechange"] = null, c.removeChild(d), d = null, p.complete(f)
				}, d.src = e, d)
			},
			load: function(e) {
				var t = a[e],
					n = t.names.shift(),
					r = t.urls.shift(),
					i = r.length,
					s = 0,
					o;
				for (; s < i; s++) o = p.create(r[s], n[s], e), c.insertBefore(o, c.firstChild)
			}
		};
		e.define = function(e, n, r) {
			typeof e != "string" ? (typeof e == "function" ? r = e : (r = n, n = e), e = p.getCurrentScript()) : n !== t && r === t && (r = n, n = null);
			var i = h.module,
				s = i[e],
				o = !1,
				u = !0,
				f = [],
				l = [],
				c = 0,
				d = 0,
				v, m, g, y, b, w, E, S, x, T, N, C, k, L;
			if (!s) {
				s = i[e] = {}, n && (s.deps = n), p.factoryHandle(e, s, r);
				return
			}
			v = s.useKey, m = a[v], g = s.url, s.status = 2, s.deps = [];
			if (n && n.length) {
				b = g.slice(0, g.lastIndexOf("/") + 1), y = m.factorys, N = m.deps[e] = {};
				for (k = 0; k < n.length; k++) {
					S = p.parseModId(n[k], b), E = S[0], w = i[E], s.deps.push(E), N[E] = !0;
					if (w) {
						w.status !== 4 && (o || (o = !0, L = E), u = !1), n.splice(k--, 1);
						continue
					}
					w = i[E] = {}, u = !1, m.length++, f[f.length++] = E, l[l.length++] = w.url = S[1]
				}
				if (!u) {
					y.unshift({
						name: e,
						factory: r
					});
					if (L) {
						C = m.deps[L];
						for (k = y.length - 1; k >= 0; k--) {
							S = y[k].name;
							if (S === L) {
								d = k;
								if (!C) break
							}
							if (C && C[S]) {
								c = k;
								break
							}
						}
						y.splice(c + 1, 0, y.splice(d, 1)[0]), y.splice(c + 1, 0, y.shift())
					}
				}
				f.length && (m.names.unshift(f), m.urls.unshift(l))
			}
			u && p.factoryHandle(e, s, r, m), p.fireFactory(v), s.deps.length || delete s.deps
		}, e.require = function(e, t) {
			e = typeof e == "string" ? [e] : e;
			var n = h.module,
				r = e.length,
				i = !0,
				s = [],
				o = [],
				u = [],
				f = 0,
				c, d, v, m, g, y, b;
			for (b = 0; b < r; b++) v = p.parseModId(e[b], l.baseUrl), d = v[0], c = n[d], c || (c = n[d] = {}, i = !1), o[o.length++] = d, u[u.length++] = c.url = v[1];
			m = o.join("_") + "_" + h.guid(), s = s.concat(o);
			if (i) {
				r = s.length, g = [];
				for (b = 0; b < r; b++) y = s[b], c = n[y], c.status !== 4 && h.error("[" + y + "] module failed to use."), g[f++] = c.exports;
				t && t.apply(null, g);
				return
			}
			a[m] = {
				length: s.length,
				namesCache: s,
				names: [o],
				urls: [u],
				callback: t,
				factorys: [],
				deps: {}
			}, p.load(m)
		}, p.init()
	}(window);