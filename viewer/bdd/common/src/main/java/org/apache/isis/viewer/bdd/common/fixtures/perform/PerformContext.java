package org.apache.isis.viewer.bdd.common.fixtures.perform;

import java.util.List;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.authentication.AuthenticationSession;
import org.apache.isis.core.metamodel.consent.Consent;
import org.apache.isis.core.metamodel.spec.feature.ObjectMember;
import org.apache.isis.viewer.bdd.common.CellBinding;
import org.apache.isis.viewer.bdd.common.StoryBoundValueException;
import org.apache.isis.viewer.bdd.common.StoryCell;
import org.apache.isis.viewer.bdd.common.fixtures.DateParser;
import org.apache.isis.viewer.bdd.common.fixtures.UsingIsisViewerPeer;

/**
 * Represents the context for a single {@link Perform} command; in effect, a row
 * of a table.
 */
public class PerformContext {

	private final UsingIsisViewerPeer peer;

	private final ObjectAdapter onAdapter;
	private final ObjectMember objectMember;
	private final List<StoryCell> argumentCells;

	public PerformContext(final UsingIsisViewerPeer peer,
			final ObjectAdapter onAdapter,
			final ObjectMember objectMember,
			final List<StoryCell> argumentCells) {
		this.onAdapter = onAdapter;
		this.objectMember = objectMember;
		this.peer = peer;
		this.argumentCells = argumentCells;
	}

	public UsingIsisViewerPeer getPeer() {
		return peer;
	}

	public ObjectAdapter getOnAdapter() {
		return onAdapter;
	}

	public ObjectMember getObjectMember() {
		return objectMember;
	}

	public List<StoryCell> getArgumentCells() {
		return argumentCells;
	}

	public Consent visibleMemberConsent() {
		return getObjectMember().isVisible(getAuthenticationSession(),
				getOnAdapter());
	}

	public Consent usableMemberConsent() {
		return getObjectMember().isUsable(getAuthenticationSession(),
				getOnAdapter());
	}

	public Consent validObjectConsent() {
		final ObjectAdapter onAdapter = getOnAdapter();
		return onAdapter.getSpecification().isValid(onAdapter);
	}

	public void ensureVisible(CellBinding onMemberBinding, final StoryCell onMemberCell)
	throws StoryBoundValueException {
		final Consent visible = objectMember.isVisible(getAuthenticationSession(),
				getOnAdapter());
		if (visible.isVetoed()) {
			throw StoryBoundValueException.current(onMemberBinding, "(not visible)");
		}
	}
	
	public void ensureUsable(CellBinding onMemberBinding, final StoryCell onMemberCell)
			throws StoryBoundValueException {
		final Consent usable = objectMember.isUsable(getAuthenticationSession(),
				getOnAdapter());
		if (usable.isVetoed()) {
			throw StoryBoundValueException.current(onMemberBinding, "(not usable)");
		}
	}

	protected AuthenticationSession getAuthenticationSession() {
		return getPeer().getAuthenticationSession();
	}

    public DateParser getDateParser() {
        return peer.getDateParser();
    }
}
